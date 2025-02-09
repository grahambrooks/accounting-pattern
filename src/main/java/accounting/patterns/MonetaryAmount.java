package accounting.patterns;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * Represents an immutable monetary amount with a specific currency.
 * This class is thread-safe and can be used as a value type.
 */
public final record MonetaryAmount(Currency currency, BigDecimal amount) {
    public static final MonetaryAmount ZERO = of("USD", 0);

    /**
     * Creates a new MonetaryAmount with validation.
     */
    public MonetaryAmount {
        Objects.requireNonNull(currency, "Currency must not be null");
        Objects.requireNonNull(amount, "Amount must not be null");
    }

    /**
     * Factory method to create a MonetaryAmount from a currency code and a long value.
     */
    public static MonetaryAmount of(String currencyCode, long amount) {
        return new MonetaryAmount(Currency.getInstance(currencyCode), BigDecimal.valueOf(amount));
    }

    /**
     * Factory method to create a MonetaryAmount from a Currency and a BigDecimal.
     */
    public static MonetaryAmount of(Currency currency, BigDecimal amount) {
        return new MonetaryAmount(currency, amount);
    }

    /**
     * Adds another MonetaryAmount to this one, ensuring currencies match.
     * @throws IllegalArgumentException if currencies don't match
     */
    public MonetaryAmount add(MonetaryAmount other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add amounts with different currencies: " + 
                currency.getCurrencyCode() + " != " + other.currency.getCurrencyCode());
        }
        return new MonetaryAmount(currency, amount.add(other.amount));
    }
}
