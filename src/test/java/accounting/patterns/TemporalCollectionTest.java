package accounting.patterns;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TemporalCollectionTest {
    @Test
    void getReturnsEmptyWhenEmpty() {
        assertEquals(Optional.empty(), new TemporalCollection<String>().get(LocalDate.now()));
    }

    @Test
    void returnsEmptyIfNoneValid() {
        final TemporalCollection<String> collection = new TemporalCollection<>();
        collection.put(LocalDate.now().plusDays(1), "foo");

        assertEquals(Optional.empty(), collection.get(LocalDate.now()));
    }

    @Test
    void returnsValueIfInRange() {
        final TemporalCollection<String> collection = new TemporalCollection<>();
        collection.put(LocalDate.now().minusDays(1), "foo");

        assertEquals(Optional.of("foo"), collection.get(LocalDate.now()));
    }

    @Test
    void returnsMostRecent() {
        final TemporalCollection<String> collection = new TemporalCollection<>();
        collection.put(LocalDate.now().minusDays(2), "Day before Yesterday");
        collection.put(LocalDate.now().minusDays(1), "Yesterday");

        assertEquals(Optional.of("Yesterday"), collection.get(LocalDate.now()));
    }

    @Test
    void returnsForSameDay() {
        final TemporalCollection<String> collection = new TemporalCollection<>();
        final LocalDate yesterday = LocalDate.now().minusDays(1);
        collection.put(yesterday, "Yesterday");

        assertEquals(Optional.of("Yesterday"), collection.get(yesterday));
    }

    @Test
    void reportsTheDateRangeItCovers() {
        final TemporalCollection<String> collection = new TemporalCollection<>();
        final LocalDate earliest = LocalDate.of(1999, 10, 1);
        final LocalDate latest = earliest.plusYears(2);
        collection.put(latest, "later");
        collection.put(earliest, "earlier");

        assertEquals(Optional.of(earliest), collection.getEarliestDate());
        assertEquals(Optional.of(latest), collection.getLatestDate());
    }

    @Test
    void hasNoDateRangeWhenEmpty() {
        final TemporalCollection<String> collection = new TemporalCollection<>();

        assertEquals(Optional.empty(), collection.getEarliestDate());
        assertEquals(Optional.empty(), collection.getLatestDate());
    }
}
