package accounting.patterns;

import java.time.LocalDate;

abstract class PostingRule {
    protected EntryType eventType;

    protected PostingRule(EntryType type) {
        this.eventType = type;
    }

    private void makeEntry(AccountingEvent event, MonetaryAmount amount) {
        event.getCustomer().addEntry(new Entry(event.getEventDate(), eventType, amount));
    }

    public void process(AccountingEvent evt) {
        makeEntry(evt, calculateAmount(evt));
    }

    protected abstract MonetaryAmount calculateAmount(AccountingEvent evt);

    protected abstract MonetaryAmount calculateAmount(Quantity quantity, double rate);

    public Entry processEvent(LocalDate eventDate, Quantity quantity, double rate) {
        return new Entry(eventDate, eventType, calculateAmount(quantity, rate));
    }
}