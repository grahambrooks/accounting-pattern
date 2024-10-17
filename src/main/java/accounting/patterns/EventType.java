package accounting.patterns;

public class EventType {
    public static EventType USAGE = eventType("usage");
    private final String name;

    public EventType(String name) {
        this.name = name;
    }

    public static EventType eventType(String name) {
        return new EventType(name);
    }

    public String getName() {
        return name;
    }
}
