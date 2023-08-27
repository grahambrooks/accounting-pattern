package accounting.patterns;

abstract class PostingRule {
    protected EntryType type;

    protected PostingRule(EntryType type) {
        this.type = type;
    }

    private void makeEntry(AccountingEvent event, MonetaryAmount amount) {
        event.getCustomer().addEntry(new Entry(event.getEventDate(), type, amount));
    }

    public void process(AccountingEvent evt) {
        makeEntry(evt, calculateAmount(evt));
    }

    protected abstract MonetaryAmount calculateAmount(AccountingEvent evt);
}