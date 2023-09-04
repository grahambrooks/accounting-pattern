package accounting.patterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static accounting.patterns.EntryType.BASE_USAGE;
import static accounting.patterns.EventType.USAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CustomerTest {

    @Test
    @DisplayName("Posting an event to a customer creates a valid entry")
    void testPostingAnEvent() {
        LocalDate effectiveDate = LocalDate.of(1999, 10, 1);
        Customer customer = new Customer("Acme Coffee Makers",
                new ServiceAgreement(10)
                        .addPostingRule(USAGE,
                                new MultiplyByRatePostingRule(BASE_USAGE),
                                effectiveDate));

        customer.post(USAGE, effectiveDate, new Quantity(50));

        Entry resultingEntry = customer.getEntry(0);
        assertEquals(new MonetaryAmount("USD", 500), resultingEntry.getAmount());
    }

    @Test
    @DisplayName("Posting an event to a customer without posting rule")
    void testCustomerWithNoRules() {
        LocalDate effectiveDate = LocalDate.of(1999, 10, 1);
        Customer customer = new Customer("Acme Coffee Makers",
                new ServiceAgreement(10));

        assertThrows(RuntimeException.class, () -> customer.post(USAGE, effectiveDate, new Quantity(50)));
    }


    @Test
    @DisplayName("Calculating entry amounts for a customer with a posting rule")
    void testUsage() {
        LocalDate effectiveDate = LocalDate.of(1999, 10, 1);
        ServiceAgreement serviceAgreement = new ServiceAgreement(10)
                .addPostingRule(USAGE,
                        new MultiplyByRatePostingRule(BASE_USAGE),
                        effectiveDate);
        Customer customer = new Customer("Acme Coffee Makers",
                serviceAgreement);

        UsageEvent usageEvent = new UsageEvent(customer, new Quantity(50), effectiveDate);

        usageEvent.process();

        Entry resultingEntry = customer.getEntry(0);
        assertEquals(new MonetaryAmount("USD", 500), resultingEntry.getAmount());
    }

    @Test
    @DisplayName("Calculating entry amounts for a customer with no posting rules throws an exception")
    void testNoPostingRuleForDate() {
        LocalDate effectiveDate = LocalDate.of(2000, 10, 1);
        LocalDate eventDate = LocalDate.of(1999, 10, 1);
        Customer customer = new Customer("Acme Coffee Makers",
                new ServiceAgreement(10)
                        .addPostingRule(USAGE,
                                new MultiplyByRatePostingRule(BASE_USAGE),
                                effectiveDate));

        UsageEvent usageEvent = new UsageEvent(customer, new Quantity(50), eventDate);

        assertThrows(RuntimeException.class, usageEvent::process);
    }

    @Test
    @DisplayName("Customers have names")
    void customersAreNamed() {
        assertEquals("foo", new Customer("foo", new ServiceAgreement(1)).getName());
    }
}