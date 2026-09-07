package accounting.patterns;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AcceptanceTest {
    @Test
    void balanceAccumulatesEveryPostedEvent() {
        LocalDate effectiveDate = LocalDate.of(1999, 10, 1);
        LocalDate balanceDate = LocalDate.of(2020, 10, 1);
        var customer = new Customer("Acme Coffee Makers",
                new ServiceAgreement(new BigDecimal(10))
                        .addPostingRule(EventType.USAGE, new MultiplyByRatePostingRule(EntryType.BASE_USAGE), effectiveDate)
                        .addPostingRule(EventType.SERVICE, new AnnualServiceFeeRule(EntryType.SERVICE, 100), effectiveDate)
                        .addPostingRule(EventType.SERVICE, new AnnualServiceFeeRule(EntryType.SERVICE, 1000), effectiveDate.plusYears(2))
        );

        customer.post(EventType.USAGE, effectiveDate.plusMonths(2), new Quantity(50));
        customer.post(EventType.SERVICE, effectiveDate.plusMonths(12), new Quantity(1));

        assertEquals(MonetaryAmount.of("USD", 600), customer.balance(balanceDate));

        customer.post(EventType.USAGE, effectiveDate.plusMonths(2), new Quantity(50));

        assertEquals(MonetaryAmount.of("USD", 1100), customer.balance(balanceDate));

        // Past the second effective date, the later service fee rule applies.
        customer.post(EventType.SERVICE, effectiveDate.plusMonths(25), new Quantity(1));

        assertEquals(MonetaryAmount.of("USD", 2100), customer.balance(balanceDate));
    }
}
