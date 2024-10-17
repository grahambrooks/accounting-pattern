package accounting.patterns;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TemporalCollection<T> {
    Map<LocalDate, T> entries = new LinkedHashMap<>();

    public void put(LocalDate date, T rule) {
        entries.put(date, rule);
    }

    public T get(LocalDate when) {
        for (LocalDate d : entries.keySet().stream().sorted(Comparator.reverseOrder()).toList()) {
            if (when.isEqual(d) || when.isAfter(d)) {
                return entries.get(d);
            }
        }
        return null;
    }
}
