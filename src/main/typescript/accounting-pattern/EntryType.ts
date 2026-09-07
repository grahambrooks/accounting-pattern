/** The kind of entry a posting rule produces. */
export class EntryType {
    static readonly BASE_USAGE = new EntryType("Base Usage");
    static readonly SERVICE = new EntryType("Service Fee");

    private readonly name: string;

    constructor(name: string) {
        this.name = name;
    }

    static entryType(name: string): EntryType {
        return new EntryType(name);
    }

    getName(): string {
        return this.name;
    }

    toString(): string {
        return `EntryType{name='${this.name}'}`;
    }
}
