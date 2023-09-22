package accounting.patterns;

import java.math.BigDecimal;
import java.util.Currency;

class MultiplyByRatePostingRule extends PostingRule {
    public MultiplyByRatePostingRule(EntryType type) {
        super(type);
    }

    @Override
    protected MonetaryAmount calculateAmount(Quantity quantity, double rate) {
        return new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(quantity.getValue() * rate));
    }
}