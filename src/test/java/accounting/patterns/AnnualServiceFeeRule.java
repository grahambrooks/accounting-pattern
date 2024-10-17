package accounting.patterns;

public class AnnualServiceFeeRule extends PostingRule {
    private final long amount;

    public AnnualServiceFeeRule(EntryType service, long amount) {
        super(service);
        this.amount = amount;
    }

    @Override
    protected MonetaryAmount calculateAmount(Quantity quantity, double rate) {
        return new MonetaryAmount("USD", quantity.getValue() * this.amount);
    }
}
