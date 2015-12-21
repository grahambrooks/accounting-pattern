package accounting.patterns;

import java.math.BigDecimal;
import java.util.Currency;

class MultiplyByRatePostingRule extends PostingRule {
    public MultiplyByRatePostingRule(EntryType type) {
        super(type);
    }

    protected MonetaryAmount calculateAmount(AccountingEvent evt) {

        Usage usageEvent = (Usage) evt;
        return new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(usageEvent.getAmount().getAmount() * usageEvent.getRate()));
    }
}