package accounting.patterns;

/**
 * Represents the type of an accounting event.
 * This enum defines the standard event types used in the accounting system.
 */
public enum EventType {
    /**
     * Represents a usage event
     */
    USAGE("usage"),

    /**
     * Represents a service fee event
     */
    SERVICE("service fee");

    private final String name;

    /**
     * Creates a new EventType with the specified name.
     *
     * @param name the name for this event type
     */
    EventType(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this event type.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the EventType with the specified name, if it exists.
     *
     * @param name the name to look up
     * @return the matching EventType
     * @throws IllegalArgumentException if no EventType matches the given name
     */
    public static EventType fromName(String name) {
        for (EventType type : values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No EventType found for name: " + name);
    }
}
