package accounting.patterns;

import java.math.BigDecimal;
import java.util.Currency;

class MultiplyByRatePostingRule extends PostingRule {
    public MultiplyByRatePostingRule(EntryType type) {
        super(type);
    }

    protected MonetaryAmount calculateAmount(AccountingEvent event) {

        UsageEvent usageEventEvent = (UsageEvent) event;
        return new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(usageEventEvent.getQuantity().getValue() * usageEventEvent.getRate()));
    }

    @Override
    protected MonetaryAmount calculateAmount(Quantity quantity, double rate) {
        return new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(quantity.getValue() * rate));
    }
}