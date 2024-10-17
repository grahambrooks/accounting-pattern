package accounting.patterns;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final ServiceAgreement serviceAgreement;
    private final String name;
    private List<Entry> entries = new ArrayList<>();

    public Customer(String name, ServiceAgreement serviceAgreement) {
        this.name = name;
        this.serviceAgreement = serviceAgreement;
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

    public String getName() {
        return name;
    }
}
