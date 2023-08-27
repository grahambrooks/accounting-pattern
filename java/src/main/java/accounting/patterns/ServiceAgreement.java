package accounting.patterns;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ServiceAgreement {
    private final double rate;
    private final Map<EventType, TemporalCollection<PostingRule>> postingRules = new HashMap<>();

    public ServiceAgreement(double rate) {
        this.rate = rate;
    }

    ServiceAgreement addPostingRule(EventType eventType, PostingRule rule, LocalDate date) {
        temporalCollection(eventType).put(date, rule);
        return this;
    }

    PostingRule getPostingRule(EventType eventType, LocalDate when) {
        return temporalCollection(eventType).get(when);
    }

    private TemporalCollection<PostingRule> temporalCollection(EventType eventType) {
        postingRules.computeIfAbsent(eventType, k -> new TemporalCollection<>());
        return postingRules.get(eventType);
    }

    public double getRate() {
        return rate;
    }
}
