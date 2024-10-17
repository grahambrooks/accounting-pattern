package accounting.patterns;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    private final ServiceAgreement serviceAgreement;
    private final List<Entry> entries = new ArrayList<>();

    public Customer(String name, ServiceAgreement serviceAgreement) {
        this.name = name;
        this.serviceAgreement = serviceAgreement;
    }

    public String getName() {
        return name;
    }

    public Entry getEntry(int index) {
        return entries.get(index);
    }

    public void post(EventType eventType, LocalDate eventDate, Quantity quantity) {
        entries.add(serviceAgreement.post(eventType, eventDate, quantity));
    }

    public MonetaryAmount balance(LocalDate balanceDate) {
        return entries.stream()
                .filter(entry -> entry.getEntryDate().isBefore(balanceDate))
                .map(Entry::getAmount)
                .reduce(MonetaryAmount.ZERO, MonetaryAmount::add);
    }
}
