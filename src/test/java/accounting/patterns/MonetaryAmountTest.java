package accounting.patterns;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonetaryAmountTest {
    @Test
    public void differentIfAmountsDiffer() throws Exception {
        var monetaryAmount1 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1));
        var monetaryAmount2 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(2));


        assertFalse(monetaryAmount1.equals(monetaryAmount2));
    }

    @Test
    public void differentIfCurrenciesDiffer() throws Exception {
        var monetaryAmount1 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1));
        var monetaryAmount2 = new MonetaryAmount(Currency.getInstance("GBP"), new BigDecimal(1));


        assertFalse(monetaryAmount1.equals(monetaryAmount2));
    }

    @Test
    public void sameIfCurrencyAndValueMatch() throws Exception {
        var monetaryAmount1 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1));
        var monetaryAmount2 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1));


        assertTrue(monetaryAmount1.equals(monetaryAmount2));
    }

    @Test
    public void hashcodeVariesByCurrencyAndValue() throws Exception {
        var monetaryAmount1 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1));
        var monetaryAmount2 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1));
        var monetaryAmount3 = new MonetaryAmount(Currency.getInstance("GBP"), new BigDecimal(1));
        var monetaryAmount4 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(2));


        assertTrue(monetaryAmount1.hashCode() == monetaryAmount2.hashCode());

        assertFalse(monetaryAmount1.hashCode() == monetaryAmount3.hashCode());
        assertFalse(monetaryAmount1.hashCode() == monetaryAmount4.hashCode());
    }
}
