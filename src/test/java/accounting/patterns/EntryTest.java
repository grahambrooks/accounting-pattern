package accounting.patterns;

import accounting.patterns.Entry;
import accounting.patterns.EntryType;
import accounting.patterns.MonetaryAmount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EntryTest {
    @Test
    public void entriesAreDated() throws Exception {
        var entry = new Entry(LocalDate.now(), EntryType.entryType("foo"), new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1)));

        assertThat(entry.getAmount(), is(new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1))));
    }
}
