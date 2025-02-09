package accounting.patterns;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a rule for posting accounting entries.
 * This abstract class defines the base functionality for creating entries based on events.
 */
abstract class PostingRule  {
    protected final EntryType eventType;

    /**
     * Creates a new PostingRule for the specified entry type.
     *
     * @param type the type of entries this rule will create
     * @throws NullPointerException if type is null
     */
    protected PostingRule(EntryType type) {
        this.eventType = Objects.requireNonNull(type, "Entry type must not be null");
    }

    /**
     * Calculates the monetary amount for an entry based on quantity and rate.
     *
     * @param quantity the quantity to calculate for
     * @param rate the rate to apply
     * @return the calculated monetary amount
     * @throws NullPointerException if quantity or rate is null
     */
     abstract MonetaryAmount calculateAmount(Quantity quantity, BigDecimal rate);

    /**
     * Processes an event and creates a corresponding entry.
     *
     * @param eventDate the date of the event
     * @param quantity the quantity involved in the event
     * @param rate the rate to apply
     * @return the created entry
     * @throws NullPointerException if any parameter is null
     */
    public Entry processEvent(LocalDate eventDate, Quantity quantity, BigDecimal rate) {
        Objects.requireNonNull(eventDate, "Event date must not be null");
        Objects.requireNonNull(quantity, "Quantity must not be null");
        Objects.requireNonNull(rate, "Rate must not be null");

        return Entry.of(eventDate, eventType, calculateAmount(quantity, rate));
    }
}
