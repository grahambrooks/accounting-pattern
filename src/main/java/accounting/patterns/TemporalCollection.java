package accounting.patterns;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TemporalCollection<T> {
    Map<LocalDate, T> entries = new LinkedHashMap<LocalDate, T>();

    public void put(LocalDate date, T rule) {
        entries.put(date, rule);
    }

    public T get(LocalDate when) {
        for (var d : entries.keySet().stream().sorted((a,b) -> b.compareTo(a)).collect(Collectors.toList())) {
            if (when.isEqual(d) || when.isAfter(d)) {
                return entries.get(d);
            }
        }
        return null;
    }
}
