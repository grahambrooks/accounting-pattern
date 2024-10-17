package accounting.patterns;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static accounting.patterns.EntryType.BASE_USAGE;
import static accounting.patterns.EventType.USAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    @Test
    public void testUsage() {
        var acm = new Customer("Acme Coffee Makers", new ServiceAgreement(10)
                .addPostingRule(USAGE, new MultiplyByRatePostingRule(BASE_USAGE), LocalDate.of(1999, 10, 1)));

        var usageEvent = new UsageEvent(new Quantity(50), LocalDate.of(1999, 10, 1), acm);
        usageEvent.process();

        var resultingEntry = acm.getEntry(0);
        assertEquals(new MonetaryAmount("USD", 500), resultingEntry.getAmount());
    }

    @Test
    public void customersAreNamed() throws Exception {
        assertEquals(new Customer("foo", new ServiceAgreement(1)).getName(), "foo");
    }
}
