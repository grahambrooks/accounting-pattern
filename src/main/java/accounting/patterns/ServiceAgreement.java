package accounting.patterns;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a service agreement with associated posting rules and rate.
 * This class manages the temporal aspects of posting rules and their application.
 */
public final class ServiceAgreement {
    private final BigDecimal rate;
    private final Map<EventType, TemporalCollection<PostingRule>> postingRules;

    /**
     * Creates a new ServiceAgreement with the specified rate.
     *
     * @param rate the rate to apply for this agreement
     * @throws IllegalArgumentException if rate is negative
     */
    public ServiceAgreement(BigDecimal rate) {
        this.rate = validateRate(rate);
        this.postingRules = new HashMap<>();
    }

    private static BigDecimal validateRate(BigDecimal rate) {
        Objects.requireNonNull(rate, "Rate must not be null");
        if (rate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Rate cannot be negative: " + rate);
        }
        return rate;
    }

    /**
     * Adds a posting rule for a specific event type and effective date.
     *
     * @param eventType the type of event this rule applies to
     * @param rule the posting rule to add
     * @param effectiveDate the date from which this rule becomes effective
     * @return this ServiceAgreement for method chaining
     * @throws NullPointerException if any parameter is null
     */
    public ServiceAgreement addPostingRule(EventType eventType, PostingRule rule, LocalDate effectiveDate) {
        Objects.requireNonNull(eventType, "Event type must not be null");
        Objects.requireNonNull(rule, "Posting rule must not be null");
        Objects.requireNonNull(effectiveDate, "Effective date must not be null");

        temporalCollection(eventType).put(effectiveDate, rule);
        return this;
    }

    /**
     * Gets the posting rule effective for the specified event type and date.
     *
     * @param eventType the type of event
     * @param when the date for which to get the rule
     * @return an Optional containing the effective posting rule, or empty if none exists
     * @throws NullPointerException if any parameter is null
     */
    public Optional<PostingRule> getPostingRule(EventType eventType, LocalDate when) {
        Objects.requireNonNull(eventType, "Event type must not be null");
        Objects.requireNonNull(when, "Date must not be null");

        return temporalCollection(eventType).get(when);
    }

    private TemporalCollection<PostingRule> temporalCollection(EventType eventType) {
        return postingRules.computeIfAbsent(eventType, k -> new TemporalCollection<>());
    }

    /**
     * Gets the rate for this service agreement.
     *
     * @return the rate as a BigDecimal
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Posts an entry based on the event type, date, and quantity.
     *
     * @param eventType the type of event to post
     * @param eventDate the date of the event
     * @param quantity the quantity involved in the event
     * @return the posted entry
     * @throws NullPointerException if any parameter is null
     * @throws IllegalStateException if no posting rule exists for the event type and date
     */
    public Entry post(EventType eventType, LocalDate eventDate, Quantity quantity) {
        Objects.requireNonNull(eventType, "Event type must not be null");
        Objects.requireNonNull(eventDate, "Event date must not be null");
        Objects.requireNonNull(quantity, "Quantity must not be null");

        PostingRule postingRule = getPostingRule(eventType, eventDate)
            .orElseThrow(() -> new IllegalStateException(
                "No posting rule found for event type '%s' on date %s"
                    .formatted(eventType, eventDate)));

        return postingRule.processEvent(eventDate, quantity, this.rate);
    }
}
