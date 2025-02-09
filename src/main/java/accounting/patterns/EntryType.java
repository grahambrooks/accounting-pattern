package accounting.patterns;

/**
 * Represents the type of an accounting entry.
 * This enum defines the standard entry types used in the accounting system.
 */
public enum EntryType {
    /**
     * Represents a base usage entry type
     */
    BASE_USAGE("Base Usage"),

    /**
     * Represents a service fee entry type
     */
    SERVICE("Service Fee");

    private final String name;

    /**
     * Creates a new EntryType with the specified display name.
     *
     * @param name the display name for this entry type
     */
    EntryType(String name) {
        this.name = name;
    }

    /**
     * Returns the display name of this entry type.
     *
     * @return the display name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the EntryType with the specified name, if it exists.
     *
     * @param name the name to look up
     * @return the matching EntryType
     * @throws IllegalArgumentException if no EntryType matches the given name
     */
    public static EntryType fromName(String name) {
        for (EntryType type : values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No EntryType found for name: " + name);
    }
}
