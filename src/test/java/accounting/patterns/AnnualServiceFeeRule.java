package accounting.patterns;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * A flat annual fee, charged per unit of quantity and independent of the
 * service agreement's rate.
 */
record AnnualServiceFeeRule(EntryType entryType, long amount) implements PostingRule {
    private static final Currency USD = Currency.getInstance("USD");

    @Override
    public MonetaryAmount calculateAmount(Quantity quantity, BigDecimal rate) {
        return MonetaryAmount.of(USD,
                BigDecimal.valueOf(quantity.value()).multiply(BigDecimal.valueOf(this.amount)));
    }
}
