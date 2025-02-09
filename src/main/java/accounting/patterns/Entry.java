package accounting.patterns;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents an immutable accounting entry with a date, type, and monetary amount.
 * This class is thread-safe and can be used as a value type.
 */
public final record Entry(
    LocalDate entryDate,
    EntryType entryType,
    MonetaryAmount amount
) {
    /**
     * Creates a new Entry with validation.
     *
     * @param entryDate the date of the entry
     * @param entryType the type of the entry
     * @param amount the monetary amount of the entry
     * @throws NullPointerException if any parameter is null
     */
    public Entry {
        Objects.requireNonNull(entryDate, "Entry date must not be null");
        Objects.requireNonNull(entryType, "Entry type must not be null");
        Objects.requireNonNull(amount, "Amount must not be null");
    }

    /**
     * Factory method to create a new Entry.
     *
     * @param entryDate the date of the entry
     * @param entryType the type of the entry
     * @param amount the monetary amount of the entry
     * @return a new Entry instance
     * @throws NullPointerException if any parameter is null
     */
    public static Entry of(LocalDate entryDate, EntryType entryType, MonetaryAmount amount) {
        return new Entry(entryDate, entryType, amount);
    }
}
