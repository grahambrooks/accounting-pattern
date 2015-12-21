package accounting.patterns;

import java.time.LocalDate;

public class Entry {
    private final LocalDate date;
    private final EntryType type;
    private final MonetaryAmount amount;

    public Entry(LocalDate date, EntryType type, MonetaryAmount amount) {
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public MonetaryAmount getAmount() {
        return amount;
    }
}
