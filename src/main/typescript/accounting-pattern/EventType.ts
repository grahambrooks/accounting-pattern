class EventType {
    static USAGE = new EventType("usage");
    static SERVICE = new EventType("service fee");
    private name: string;

    constructor(name: string) {
        this.name = name;
    }

    static eventType(name: string): EventType {
        return new EventType(name);
    }

    getName(): string {
        return this.name;
    }

    toString(): string {
        return `EventType{name='${this.name}'}`;
    }
}
