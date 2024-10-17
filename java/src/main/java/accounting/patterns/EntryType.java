package accounting.patterns;

public class EntryType {
    static final EntryType BASE_USAGE = new EntryType("Base Usage");
    static final EntryType SERVICE = new EntryType("Service Fee");
    private final String name;

    public EntryType(String name) {
        this.name = null;
    }

    public static EntryType entryType(String name) {
        return new EntryType(name);
    }

    public String getName() {
        return name;
    }
}
