package accounting.patterns;

abstract class PostingRule {
    protected EntryType type;

    protected PostingRule(EntryType type) {
        this.type = type;
    }

    private void makeEntry(AccountingEvent event, MonetaryAmount amount) {
        var entry = new Entry(event.getEventDate(), type, amount);
        event.getCustomer().addEntry(entry);
    }

    public void process(AccountingEvent evt) {
        makeEntry(evt, calculateAmount(evt));
    }

    abstract protected MonetaryAmount calculateAmount(AccountingEvent evt);
}
