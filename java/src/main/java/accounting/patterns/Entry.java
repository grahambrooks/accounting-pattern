package accounting.patterns;

import java.time.LocalDate;

public class Entry {
    private final LocalDate date;
    private final EntryType entryType;
    private final MonetaryAmount amount;

    public Entry(LocalDate date, EntryType entryType, MonetaryAmount amount) {
        this.date = date;
        this.entryType = entryType;
        this.amount = amount;
    }

    public MonetaryAmount getAmount() {
        return amount;
    }
}
