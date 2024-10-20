package accounting.patterns;

import java.math.BigDecimal;
import java.util.Currency;

public class MonetaryAmount {
    public static final MonetaryAmount ZERO = new MonetaryAmount("USD", 0);
    private final Currency currency;
    private final BigDecimal amount;

    public MonetaryAmount(String currencyName, long amount) {
        this(Currency.getInstance(currencyName), new BigDecimal(amount));
    }

    public MonetaryAmount(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public MonetaryAmount add(MonetaryAmount other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currencies must be the same");
        }
        return new MonetaryAmount(currency, amount.add(other.amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonetaryAmount that = (MonetaryAmount) o;

        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        return amount != null ? amount.equals(that.amount) : that.amount == null;

    }

    @Override
    public int hashCode() {
        int result = currency != null ? currency.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    public BigDecimal amount() {
        return amount;
    }
}
