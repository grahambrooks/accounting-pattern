package accounting.patterns;

import accounting.patterns.TemporalCollection;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class TemporalCollectionTest {
    @Test
    public void getReturnsNullWhenEmpty() {
        assertThat(new TemporalCollection<String>().get(LocalDate.now()), is(nullValue()));
    }

    @Test
    public void returnsNullIfNoneValid() {
        final TemporalCollection<String> collection = new TemporalCollection<>();
        collection.put(LocalDate.now().plusDays(1), "foo");

        assertThat(collection.get(LocalDate.now()), is(nullValue()));
    }

    @Test
    public void returnsValueIfInRange() {
        final TemporalCollection<String> collection = new TemporalCollection<>();
        collection.put(LocalDate.now().minusDays(1), "foo");

        assertThat(collection.get(LocalDate.now()), is("foo"));
    }

    @Test
    public void returnsMostRecent() {
        final TemporalCollection<String> collection = new TemporalCollection<>();
        collection.put(LocalDate.now().minusDays(2), "Day before Yesterday");
        collection.put(LocalDate.now().minusDays(1), "Yesterday");

        assertThat(collection.get(LocalDate.now()), is("Yesterday"));
    }

    @Test
    public void returnsForSameDay() {
        final TemporalCollection<String> collection = new TemporalCollection<>();
        final LocalDate yesterday = LocalDate.now().minusDays(1);
        collection.put(yesterday, "Yesterday");

        assertThat(collection.get(yesterday), is("Yesterday"));
    }

}