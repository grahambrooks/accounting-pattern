package accounting.patterns;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * A posting rule that calculates the monetary amount by multiplying a quantity by a rate.
 *
 * <p>The product is handed to {@link MonetaryAmount}, which rounds it to the
 * currency's scale.
 *
 * @param entryType the type of entries this rule creates
 */
public record MultiplyByRatePostingRule(EntryType entryType) implements PostingRule {
    private static final Currency DEFAULT_CURRENCY = Currency.getInstance("USD");

    /**
     * Creates a new MultiplyByRatePostingRule for the specified entry type.
     *
     * @throws NullPointerException if entryType is null
     */
    public MultiplyByRatePostingRule {
        Objects.requireNonNull(entryType, "Entry type must not be null");
    }

    @Override
    public MonetaryAmount calculateAmount(Quantity quantity, BigDecimal rate) {
        Objects.requireNonNull(quantity, "Quantity must not be null");
        Objects.requireNonNull(rate, "Rate must not be null");

        return MonetaryAmount.of(DEFAULT_CURRENCY, BigDecimal.valueOf(quantity.value()).multiply(rate));
    }
}
