class MultiplyByRatePostingRule extends PostingRule {
    constructor(type: EntryType) {
        super(type);
    }

    protected calculateAmount(quantity: Quantity, rate: number): MonetaryAmount {
        return new MonetaryAmount("USD", quantity.getValue() * rate);
    }
}
