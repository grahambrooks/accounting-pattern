package accounting.patterns;

public class EventType extends NamedObject {
    public static EventType USAGE = eventType("usage");

    public EventType(String name) {
        super(name);
    }

    public static EventType eventType(String name) {
        return new EventType(name);
    }
}
