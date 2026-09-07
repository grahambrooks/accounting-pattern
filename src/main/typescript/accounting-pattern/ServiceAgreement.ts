import type { Entry } from "./Entry";
import type { EventType } from "./EventType";
import type { PostingRule } from "./PostingRule";
import type { Quantity } from "./Quantity";
import { TemporalCollection } from "./TemporalCollection";

/** The posting rules in force for a customer, and when each took effect. */
export class ServiceAgreement {
    private readonly rate: number;
    // Keyed by event type name: a Map compares object keys by identity, so
    // EventType.eventType("usage") would not find EventType.USAGE's rules.
    private readonly postingRules = new Map<string, TemporalCollection<PostingRule>>();

    constructor(rate: number) {
        this.rate = rate;
    }

    addPostingRule(eventType: EventType, rule: PostingRule, effectiveDate: Date): ServiceAgreement {
        this.temporalCollection(eventType).put(effectiveDate, rule);
        return this;
    }

    getPostingRule(eventType: EventType, when: Date): PostingRule | null {
        return this.temporalCollection(eventType).get(when);
    }

    private temporalCollection(eventType: EventType): TemporalCollection<PostingRule> {
        const key = eventType.getName();
        let rules = this.postingRules.get(key);
        if (!rules) {
            rules = new TemporalCollection<PostingRule>();
            this.postingRules.set(key, rules);
        }
        return rules;
    }

    getRate(): number {
        return this.rate;
    }

    post(eventType: EventType, eventDate: Date, quantity: Quantity): Entry {
        const postingRule = this.getPostingRule(eventType, eventDate);
        if (!postingRule) {
            throw new Error(`No posting rule for ${eventType} on ${eventDate.toISOString()}`);
        }
        return postingRule.processEvent(eventDate, quantity, this.rate);
    }
}
