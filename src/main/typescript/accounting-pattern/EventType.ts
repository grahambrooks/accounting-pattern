/** The kind of event a customer posts against a service agreement. */
export class EventType {
    static readonly USAGE = new EventType("usage");
    static readonly SERVICE = new EventType("service fee");

    private readonly name: string;

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
