package accounting.patterns;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest {
    @Test
    public void test() {
        LocalDate effectiveDate = LocalDate.of(1999, 10, 1);
        var customer = new Customer("Acme Coffee Makers",
                new ServiceAgreement(10)
                        .addPostingRule(EventType.USAGE, new MultiplyByRatePostingRule(EntryType.BASE_USAGE), effectiveDate)
                        .addPostingRule(EventType.SERVICE, new AnnualServiceFeeRule(EntryType.SERVICE, 100), effectiveDate)
                        .addPostingRule(EventType.SERVICE, new AnnualServiceFeeRule(EntryType.SERVICE, 1000), effectiveDate.plusYears(2))
        );

        customer.post(EventType.USAGE, effectiveDate.plusMonths(2), new Quantity(50));
        customer.post(EventType.SERVICE, effectiveDate.plusMonths(12), new Quantity(1));

        assertEquals(new BigDecimal(600), customer.balance(LocalDate.of(2020, 10, 1)).amount());

        customer.post(EventType.USAGE, effectiveDate.plusMonths(2), new Quantity(50));

        assertEquals(new BigDecimal(1100), customer.balance(LocalDate.of(2020, 10, 1)).amount());

        customer.post(EventType.SERVICE, effectiveDate.plusMonths(25), new Quantity(1));
        assertEquals(new BigDecimal(2100), customer.balance(LocalDate.of(2020, 10, 1)).amount());
    }
}
