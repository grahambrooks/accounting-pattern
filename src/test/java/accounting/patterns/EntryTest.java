package accounting.patterns;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntryTest {
    @Test
    public void entriesAreDated() throws Exception {
        var entry = new Entry(LocalDate.now(), EntryType.entryType("foo"), new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1)));

        assertEquals(new MonetaryAmount(Currency.getInstance("USD"), new BigDecimal(1)), entry.getAmount());
    }
}
