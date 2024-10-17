package accounting.patterns;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TemporalCollectionTest {
    @Test
    public void getReturnsNullWhenEmpty() {
        assertNull(new TemporalCollection<String>().get(LocalDate.now()));
    }

    @Test
    public void returnsNullIfNoneValid() {
        var collection = new TemporalCollection<String>();
        collection.put(LocalDate.now().plusDays(1), "foo");

        assertNull(collection.get(LocalDate.now()));
    }

    @Test
    public void returnsValueIfInRange() {
        var collection = new TemporalCollection<String>();
        collection.put(LocalDate.now().minusDays(1), "foo");

        assertEquals(collection.get(LocalDate.now()), "foo");
    }

    @Test
    public void returnsMostRecent() {
        var collection = new TemporalCollection<String>();
        collection.put(LocalDate.now().minusDays(2), "Day before Yesterday");
        collection.put(LocalDate.now().minusDays(1), "Yesterday");

        assertEquals(collection.get(LocalDate.now()), "Yesterday");
    }

    @Test
    public void returnsForSameDay() {
        var collection = new TemporalCollection<String>();
        var yesterday = LocalDate.now().minusDays(1);
        collection.put(yesterday, "Yesterday");

        assertEquals(collection.get(yesterday), "Yesterday");
    }

}
