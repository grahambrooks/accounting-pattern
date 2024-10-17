package accounting.patterns;

import accounting.patterns.MonetaryAmount;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MonetaryAmountTest {
    @Test
    public void differentIfAmountsDiffer() throws Exception {
        var monetaryAmount1 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1));
        var monetaryAmount2 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(2));


        assertThat(monetaryAmount1.equals(monetaryAmount2), is(false));
    }

    @Test
    public void differentIfCurrenciesDiffer() throws Exception {
        var monetaryAmount1 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1));
        var monetaryAmount2 = new MonetaryAmount(Currency.getInstance("GBP"), new BigDecimal(1));


        assertThat(monetaryAmount1.equals(monetaryAmount2), is(false));
    }

    @Test
    public void sameIfCurrencyAndValueMatch() throws Exception {
        var monetaryAmount1 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1));
        var monetaryAmount2 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1));


        assertThat(monetaryAmount1.equals(monetaryAmount2), is(true));
    }

    @Test
    public void hashcodeVariesByCurrencyAndValue() throws Exception {
        var monetaryAmount1 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1));
        var monetaryAmount2 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1));
        var monetaryAmount3 = new MonetaryAmount(Currency.getInstance("GBP"), new BigDecimal(1));
        var monetaryAmount4 = new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(2));


        assertThat(monetaryAmount1.hashCode() == monetaryAmount2.hashCode(), is(true));

        assertThat(monetaryAmount1.hashCode() == monetaryAmount3.hashCode(), is(false));
        assertThat(monetaryAmount1.hashCode() == monetaryAmount4.hashCode(), is(false));
    }
}
