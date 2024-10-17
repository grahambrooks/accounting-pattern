package accounting.patterns;

import java.time.LocalDate;

public class Entry {
    private final LocalDate entryDate;
    private final EntryType entryType;
    private final MonetaryAmount amount;

    public Entry(LocalDate entryDate, EntryType entryType, MonetaryAmount amount) {
        this.entryDate = entryDate;
        this.entryType = entryType;
        this.amount = amount;
    }

    public MonetaryAmount getAmount() {
        return amount;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }
}
