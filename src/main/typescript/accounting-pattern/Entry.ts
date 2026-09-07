import type { EntryType } from "./EntryType";
import type { MonetaryAmount } from "./MonetaryAmount";

/** A single posting on a customer's account. */
export class Entry {
    private readonly entryDate: Date;
    private readonly entryType: EntryType;
    private readonly amount: MonetaryAmount;

    constructor(entryDate: Date, entryType: EntryType, amount: MonetaryAmount) {
        this.entryDate = entryDate;
        this.entryType = entryType;
        this.amount = amount;
    }

    getAmount(): MonetaryAmount {
        return this.amount;
    }

    getEntryDate(): Date {
        return this.entryDate;
    }

    getEntryType(): EntryType {
        return this.entryType;
    }
}
