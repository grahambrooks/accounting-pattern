package accounting.patterns;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

/**
 * A posting rule that calculates the monetary amount by multiplying a quantity by a rate.
 * This implementation uses high-precision decimal arithmetic suitable for financial calculations.
 */
final class MultiplyByRatePostingRule extends PostingRule {
    private static final Currency DEFAULT_CURRENCY = Currency.getInstance("USD");
    private static final int DECIMAL_SCALE = 2;

    /**
     * Creates a new MultiplyByRatePostingRule for the specified entry type.
     *
     * @param type the type of entries this rule will create
     * @throws NullPointerException if type is null
     */
    public MultiplyByRatePostingRule(EntryType type) {
        super(type);
    }

    /**
     * Calculates the monetary amount by multiplying the quantity by the rate.
     * The result is rounded to 2 decimal places using {@link RoundingMode#HALF_UP}.
     *
     * @param quantity the quantity to multiply
     * @param rate the rate to multiply by
     * @return the calculated monetary amount in USD
     * @throws NullPointerException if quantity or rate is null
     */
    @Override
    protected MonetaryAmount calculateAmount(Quantity quantity, BigDecimal rate) {
        Objects.requireNonNull(quantity, "Quantity must not be null");
        Objects.requireNonNull(rate, "Rate must not be null");

        BigDecimal result = BigDecimal.valueOf(quantity.value())
                .multiply(rate)
                .setScale(DECIMAL_SCALE, RoundingMode.HALF_UP);

        return MonetaryAmount.of(DEFAULT_CURRENCY, result);
    }
}
