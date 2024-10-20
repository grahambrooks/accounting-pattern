class EntryType {
    static BASE_USAGE = new EntryType("Base Usage");
    static SERVICE = new EntryType("Service Fee");
    private name: string;

    constructor(name: string) {
        this.name = name;
    }

    static entryType(name: string): EntryType {
        return new EntryType(name);
    }

    getName(): string {
        return this.name;
    }
}
