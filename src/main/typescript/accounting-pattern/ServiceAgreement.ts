class ServiceAgreement {
    private rate: number;
    private postingRules: Map<EventType, TemporalCollection<PostingRule>>;

    constructor(rate: number) {
        this.rate = rate;
        this.postingRules = new Map<EventType, TemporalCollection<PostingRule>>();
    }

    addPostingRule(eventType: EventType, rule: PostingRule, effectiveDate: Date): ServiceAgreement {
        this.temporalCollection(eventType).put(effectiveDate, rule);
        return this;
    }

    getPostingRule(eventType: EventType, when: Date): PostingRule | null {
        return this.temporalCollection(eventType).get(when);
    }

    private temporalCollection(eventType: EventType): TemporalCollection<PostingRule> {
        if (!this.postingRules.has(eventType)) {
            this.postingRules.set(eventType, new TemporalCollection<PostingRule>());
        }
        return this.postingRules.get(eventType)!;
    }

    getRate(): number {
        return this.rate;
    }

    post(eventType: EventType, eventDate: Date, quantity: Quantity): Entry {
        const postingRule = this.getPostingRule(eventType, eventDate);

        if (!postingRule) {
            throw new Error(`No posting rule for ${eventType} on ${eventDate}`);
        }
        return postingRule.processEvent(eventDate, quantity, this.rate);
    }
}
