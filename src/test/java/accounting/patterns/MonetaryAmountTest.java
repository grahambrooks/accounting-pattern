package accounting.patterns;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Currency;

class MonetaryAmountTest {
    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency GBP = Currency.getInstance("GBP");

    @Test
    void testZeroConstant() {
        assertEquals(BigDecimal.ZERO, MonetaryAmount.ZERO.amount());
        assertEquals("USD", MonetaryAmount.ZERO.currency().getCurrencyCode());
    }

    @Test
    void testFactoryMethodWithLong() {
        MonetaryAmount amount = MonetaryAmount.of("USD", 100);
        assertEquals(new BigDecimal(100), amount.amount());
        assertEquals(USD, amount.currency());
    }

    @Test
    void testFactoryMethodWithBigDecimal() {
        BigDecimal value = new BigDecimal("10.50");
        MonetaryAmount amount = MonetaryAmount.of(USD, value);
        assertEquals(value, amount.amount());
        assertEquals(USD, amount.currency());
    }

    @Test
    void testAddition() {
        MonetaryAmount amount1 = MonetaryAmount.of(USD, new BigDecimal("10.50"));
        MonetaryAmount amount2 = MonetaryAmount.of(USD, new BigDecimal("5.25"));
        MonetaryAmount sum = amount1.add(amount2);
        assertEquals(new BigDecimal("15.75"), sum.amount());
        assertEquals(USD, sum.currency());
    }

    @Test
    void testAdditionWithDifferentCurrenciesFails() {
        MonetaryAmount usd = MonetaryAmount.of(USD, BigDecimal.TEN);
        MonetaryAmount gbp = MonetaryAmount.of(GBP, BigDecimal.TEN);
        assertThrows(IllegalArgumentException.class, () -> usd.add(gbp));
    }

    @Test
    void testNullValidation() {
        assertAll(
            () -> assertThrows(NullPointerException.class, () -> MonetaryAmount.of((Currency) null, BigDecimal.TEN)),
            () -> assertThrows(NullPointerException.class, () -> MonetaryAmount.of(USD, null)),
            () -> assertThrows(NullPointerException.class, () -> MonetaryAmount.of((String) null, 100))
        );
    }

    @Test
    void testEquality() {
        MonetaryAmount amount1 = MonetaryAmount.of(USD, BigDecimal.ONE);
        MonetaryAmount amount2 = MonetaryAmount.of(USD, BigDecimal.ONE);
        MonetaryAmount amount3 = MonetaryAmount.of(USD, BigDecimal.TEN);
        MonetaryAmount amount4 = MonetaryAmount.of(GBP, BigDecimal.ONE);

        assertAll(
            () -> assertEquals(amount1, amount2),
            () -> assertNotEquals(amount1, amount3),
            () -> assertNotEquals(amount1, amount4),
            () -> assertEquals(amount1.hashCode(), amount2.hashCode()),
            () -> assertNotEquals(amount1.hashCode(), amount3.hashCode()),
            () -> assertNotEquals(amount1.hashCode(), amount4.hashCode())
        );
    }
}
