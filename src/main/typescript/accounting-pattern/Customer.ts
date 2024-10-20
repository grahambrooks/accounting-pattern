class Customer {
    private name: string;
    private serviceAgreement: ServiceAgreement;
    private entries: Entry[] = [];

    constructor(name: string, serviceAgreement: ServiceAgreement) {
        this.name = name;
        this.serviceAgreement = serviceAgreement;
    }

    getName(): string {
        return this.name;
    }

    getEntry(index: number): Entry {
        return this.entries[index];
    }

    post(eventType: EventType, eventDate: Date, quantity: Quantity): void {
        this.entries.push(this.serviceAgreement.post(eventType, eventDate, quantity));
    }

    balance(balanceDate: Date): MonetaryAmount {
        return this.entries
            .filter(entry => entry.getEntryDate() < balanceDate)
            .map(entry => entry.getAmount())
            .reduce((acc, amount) => acc.add(amount), MonetaryAmount.ZERO);
    }
}
