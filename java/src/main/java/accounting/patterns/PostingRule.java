package accounting.patterns;

import java.time.LocalDate;

abstract class PostingRule {
    protected EntryType eventType;

    protected PostingRule(EntryType type) {
        this.eventType = type;
    }

    protected abstract MonetaryAmount calculateAmount(Quantity quantity, double rate);

    public Entry processEvent(LocalDate eventDate, Quantity quantity, double rate) {
        return new Entry(eventDate, eventType, calculateAmount(quantity, rate));
    }
}