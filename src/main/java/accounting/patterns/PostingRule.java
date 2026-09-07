package accounting.patterns;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A rule for turning an event into an accounting entry.
 *
 * <p>Implementations supply the entry type they produce and the arithmetic that
 * turns a quantity and a rate into an amount; assembling the {@link Entry} is
 * common to all of them and lives here.
 */
public interface PostingRule {
    /**
     * The type of the entries this rule creates.
     *
     * @return the entry type
     */
    EntryType entryType();

    /**
     * Calculates the monetary amount for an entry based on quantity and rate.
     *
     * @param quantity the quantity to calculate for
     * @param rate the rate to apply
     * @return the calculated monetary amount
     * @throws NullPointerException if quantity or rate is null
     */
    MonetaryAmount calculateAmount(Quantity quantity, BigDecimal rate);

    /**
     * Processes an event and creates a corresponding entry.
     *
     * @param eventDate the date of the event
     * @param quantity the quantity involved in the event
     * @param rate the rate to apply
     * @return the created entry
     * @throws NullPointerException if any parameter is null
     */
    default Entry processEvent(LocalDate eventDate, Quantity quantity, BigDecimal rate) {
        Objects.requireNonNull(eventDate, "Event date must not be null");
        Objects.requireNonNull(quantity, "Quantity must not be null");
        Objects.requireNonNull(rate, "Rate must not be null");

        return Entry.of(eventDate, entryType(), calculateAmount(quantity, rate));
    }
}
