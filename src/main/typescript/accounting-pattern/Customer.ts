import type { Entry } from "./Entry";
import type { EventType } from "./EventType";
import { MonetaryAmount } from "./MonetaryAmount";
import type { Quantity } from "./Quantity";
import type { ServiceAgreement } from "./ServiceAgreement";

/** An account holder, its service agreement, and the entries posted to it. */
export class Customer {
    private readonly name: string;
    private readonly serviceAgreement: ServiceAgreement;
    private readonly entries: Entry[] = [];

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
            .reduce((total, amount) => total.add(amount), MonetaryAmount.ZERO);
    }
}
