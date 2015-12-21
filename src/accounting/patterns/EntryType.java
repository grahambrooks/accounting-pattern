package accounting.patterns;

public class EntryType extends NamedObject {
    static EntryType BASE_USAGE = new EntryType("Base Usage");
    static EntryType SERVICE = new EntryType("Service Fee");

    public EntryType(String name) {
        super(name);
    }

    public static EntryType entryType(String name) {
        return new EntryType(name);
    }
}
