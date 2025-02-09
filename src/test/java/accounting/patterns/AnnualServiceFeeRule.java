package accounting.patterns;

import java.math.BigDecimal;
import java.util.Currency;

public class AnnualServiceFeeRule extends PostingRule {
    private final long amount;

    public AnnualServiceFeeRule(EntryType service, long amount) {
        super(service);
        this.amount = amount;
    }

    @Override
    protected MonetaryAmount calculateAmount(Quantity quantity, BigDecimal rate) {
        return new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(quantity.value() * this.amount));
    }
}
