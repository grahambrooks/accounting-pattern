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

    public ServiceAgreement getServiceAgreement() {
        return serviceAgreement;
    }

    public void addEntry(Entry newEntry) {
        entries.add(newEntry);

    }

    public Entry getEntry(int index) {
        return entries.get(index);
    }

    public void post(EventType eventType, LocalDate eventDate, Quantity quantity) {
        addEntry(getServiceAgreement().post(eventType, eventDate, quantity));
    }
}
