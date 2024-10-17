package accounting.patterns;

import accounting.patterns.TemporalCollection;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class TemporalCollectionTest {
    @Test
    public void getReturnsNullWhenEmpty() {
        assertThat(new TemporalCollection<String>().get(LocalDate.now()), is(nullValue()));
    }

    @Test
    public void returnsNullIfNoneValid() {
        var collection = new TemporalCollection<String>();
        collection.put(LocalDate.now().plusDays(1), "foo");

        assertThat(collection.get(LocalDate.now()), is(nullValue()));
    }

    @Test
    public void returnsValueIfInRange() {
        var collection = new TemporalCollection<String>();
        collection.put(LocalDate.now().minusDays(1), "foo");

        assertThat(collection.get(LocalDate.now()), is("foo"));
    }

    @Test
    public void returnsMostRecent() {
        var collection = new TemporalCollection<String>();
        collection.put(LocalDate.now().minusDays(2), "Day before Yesterday");
        collection.put(LocalDate.now().minusDays(1), "Yesterday");

        assertThat(collection.get(LocalDate.now()), is("Yesterday"));
    }

    @Test
    public void returnsForSameDay() {
        var collection = new TemporalCollection<String>();
        var yesterday = LocalDate.now().minusDays(1);
        collection.put(yesterday, "Yesterday");

        assertThat(collection.get(yesterday), is("Yesterday"));
    }

}
