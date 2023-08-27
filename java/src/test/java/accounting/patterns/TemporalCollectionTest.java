package accounting.patterns;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class TemporalCollectionTest {
    @Test
    void getReturnsNullWhenEmpty() {
        assertNull(new TemporalCollection<String>().get(LocalDate.now()));
    }

    @Test
    void returnsNullIfNoneValid() {
        final TemporalCollection<String> collection = new TemporalCollection<>();
        collection.put(LocalDate.now().plusDays(1), "foo");

        assertNull(collection.get(LocalDate.now()));
    }

    @Test
    void returnsValueIfInRange() {
        final TemporalCollection<String> collection = new TemporalCollection<>();
        collection.put(LocalDate.now().minusDays(1), "foo");

        assertEquals("foo", collection.get(LocalDate.now()));
    }

    @Test
    void returnsMostRecent() {
        final TemporalCollection<String> collection = new TemporalCollection<>();
        collection.put(LocalDate.now().minusDays(2), "Day before Yesterday");
        collection.put(LocalDate.now().minusDays(1), "Yesterday");

        assertEquals("Yesterday", collection.get(LocalDate.now()));
    }

    @Test
    void returnsForSameDay() {
        final TemporalCollection<String> collection = new TemporalCollection<>();
        final LocalDate yesterday = LocalDate.now().minusDays(1);
        collection.put(yesterday, "Yesterday");

        assertEquals("Yesterday", collection.get(yesterday));
    }
}