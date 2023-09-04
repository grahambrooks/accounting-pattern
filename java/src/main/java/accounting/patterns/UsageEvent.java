package accounting.patterns;

import java.time.LocalDate;

public class UsageEvent extends AccountingEvent {
    private final Quantity quantity;

    public UsageEvent(Customer customer, Quantity quantity, LocalDate date) {
        super(EventType.USAGE, date, customer);
        this.quantity = quantity;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public double getRate() {
        return getCustomer().getServiceAgreement().getRate();
    }
}