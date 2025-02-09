package accounting.patterns;

import java.time.LocalDate;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

/**
 * A collection that associates values with time periods.
 * Each value becomes effective from its associated date forward,
 * until superseded by a later entry.
 *
 * @param <T> the type of values stored in this collection
 */
public final class TemporalCollection<T> {
    private final NavigableMap<LocalDate, T> entries;

    /**
     * Creates a new empty TemporalCollection.
     */
    public TemporalCollection() {
        this.entries = new TreeMap<>();
    }

    /**
     * Associates a value with a date, making it effective from that date forward.
     *
     * @param date the effective date for the value
     * @param value the value to store
     * @throws NullPointerException if date or value is null
     */
    public void put(LocalDate date, T value) {
        Objects.requireNonNull(date, "Date must not be null");
        Objects.requireNonNull(value, "Value must not be null");
        entries.put(date, value);
    }

    /**
     * Gets the value effective on the specified date.
     * Returns the value with the most recent effective date
     * that is equal to or before the specified date.
     *
     * @param date the date to look up
     * @return an Optional containing the effective value, or empty if no value is effective
     * @throws NullPointerException if date is null
     */
    public Optional<T> get(LocalDate date) {
        Objects.requireNonNull(date, "Date must not be null");
        Map.Entry<LocalDate, T> entry = entries.floorEntry(date);
        return Optional.ofNullable(entry != null ? entry.getValue() : null);
    }

    /**
     * Gets the raw value effective on the specified date.
     * This method is provided for backward compatibility and internal use.
     * New code should prefer the Optional-returning {@link #get(LocalDate)} method.
     *
     * @param date the date to look up
     * @return the effective value, or null if no value is effective
     * @throws NullPointerException if date is null
     */
    T getRaw(LocalDate date) {
        return get(date).orElse(null);
    }

    /**
     * Returns the number of entries in this collection.
     *
     * @return the number of entries
     */
    public int size() {
        return entries.size();
    }

    /**
     * Checks if this collection is empty.
     *
     * @return true if this collection contains no entries
     */
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    /**
     * Gets the earliest effective date in this collection.
     *
     * @return an Optional containing the earliest date, or empty if the collection is empty
     */
    public Optional<LocalDate> getEarliestDate() {
        return Optional.ofNullable(entries.firstKey());
    }

    /**
     * Gets the latest effective date in this collection.
     *
     * @return an Optional containing the latest date, or empty if the collection is empty
     */
    public Optional<LocalDate> getLatestDate() {
        return Optional.ofNullable(entries.lastKey());
    }
}
