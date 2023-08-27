package accounting.patterns;

import org.junit.Test;

import java.time.LocalDate;

import static accounting.patterns.EntryType.BASE_USAGE;
import static accounting.patterns.EventType.USAGE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    @Test
    public void testUsage() {
        Customer acm = new Customer("Acme Coffee Makers", new ServiceAgreement(10)
                .addPostingRule(USAGE, new MultiplyByRatePostingRule(BASE_USAGE), LocalDate.of(1999, 10, 1)));

        UsageEvent usageEvent = new UsageEvent(new Quantity(50), LocalDate.of(1999, 10, 1), acm);
        usageEvent.process();

        Entry resultingEntry = acm.getEntry(0);
        assertThat(new MonetaryAmount("USD", 500), is(resultingEntry.getAmount()));
    }

    @Test
    public void customersAreNamed() throws Exception {
        assertThat(new Customer("foo", new ServiceAgreement(1)).getName(), is("foo"));
    }
}