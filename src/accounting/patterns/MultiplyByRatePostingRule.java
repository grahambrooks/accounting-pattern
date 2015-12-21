package accounting.patterns;

import java.math.BigDecimal;
import java.util.Currency;

class MultiplyByRatePostingRule extends PostingRule {
    public MultiplyByRatePostingRule(EntryType type) {
        super(type);
    }

    protected MonetaryAmount calculateAmount(AccountingEvent evt) {

        UsageEvent usageEventEvent = (UsageEvent) evt;
        return new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(usageEventEvent.getAmount().getAmount() * usageEventEvent.getRate()));
    }
}