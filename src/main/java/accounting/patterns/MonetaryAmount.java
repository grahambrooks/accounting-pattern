package accounting.patterns;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

/**
 * Represents an immutable monetary amount with a specific currency.
 * This record is thread-safe and can be used as a value type.
 *
 * <p>The amount is normalised on construction to the number of decimal places
 * the currency actually uses ({@link Currency#getDefaultFractionDigits()}), so
 * two amounts that represent the same sum of money are always {@code equals},
 * regardless of the scale of the {@link BigDecimal} they were built from.
 * {@code BigDecimal} equality is scale-sensitive — {@code 500} and
 * {@code 500.00} are not equal — which makes an un-normalised money type
 * surprising to compare.
 */
public record MonetaryAmount(Currency currency, BigDecimal amount) {
    /**
     * The rounding applied when an amount carries more precision than its
     * currency can represent.
     */
    private static final RoundingMode ROUNDING = RoundingMode.HALF_UP;

    /**
     * Zero US dollars, the identity for {@link #add(MonetaryAmount)} in USD.
     */
    public static final MonetaryAmount ZERO = of("USD", 0);

    /**
     * Creates a new MonetaryAmount, validating its parts and normalising the
     * amount to the currency's scale.
     *
     * @throws NullPointerException if currency or amount is null
     */
    public MonetaryAmount {
        Objects.requireNonNull(currency, "Currency must not be null");
        Objects.requireNonNull(amount, "Amount must not be null");
        amount = normalise(currency, amount);
    }

    private static BigDecimal normalise(Currency currency, BigDecimal amount) {
        int fractionDigits = currency.getDefaultFractionDigits();
        // Pseudo-currencies such as XXX report -1; leave those amounts untouched.
        return fractionDigits < 0 ? amount : amount.setScale(fractionDigits, ROUNDING);
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
     *
     * @throws IllegalArgumentException if currencies don't match
     */
    public MonetaryAmount add(MonetaryAmount other) {
        Objects.requireNonNull(other, "Amount to add must not be null");
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException(
                    "Cannot add amounts with different currencies: %s != %s"
                            .formatted(currency.getCurrencyCode(), other.currency.getCurrencyCode()));
        }
        return new MonetaryAmount(currency, amount.add(other.amount));
    }
}
