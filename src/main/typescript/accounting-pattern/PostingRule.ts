abstract class PostingRule {
    protected eventType: EntryType;

    protected constructor(type: EntryType) {
        this.eventType = type;
    }

    protected abstract calculateAmount(quantity: Quantity, rate: number): MonetaryAmount;

    public processEvent(eventDate: Date, quantity: Quantity, rate: number): Entry {
        return new Entry(eventDate, this.eventType, this.calculateAmount(quantity, rate));
    }
}
