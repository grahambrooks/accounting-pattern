package accounting.patterns;

import java.time.LocalDate;

public class Usage extends AccountingEvent {
    private final Quantity amount;

    public Usage(Quantity amount, LocalDate date, Customer customer) {
        super(EventType.USAGE, date, customer);
        this.amount = amount;
    }

    public Quantity getAmount() {
        return amount;
    }

    public double getRate() {
        return getCustomer().getServiceAgreement().getRate();
    }
}