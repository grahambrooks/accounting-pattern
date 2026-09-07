import { Entry } from "./Entry";
import type { EntryType } from "./EntryType";
import type { MonetaryAmount } from "./MonetaryAmount";
import type { Quantity } from "./Quantity";

/** Turns an event into the entry it should post. */
export abstract class PostingRule {
    protected readonly entryType: EntryType;

    protected constructor(type: EntryType) {
        this.entryType = type;
    }

    protected abstract calculateAmount(quantity: Quantity, rate: number): MonetaryAmount;

    processEvent(eventDate: Date, quantity: Quantity, rate: number): Entry {
        return new Entry(eventDate, this.entryType, this.calculateAmount(quantity, rate));
    }
}
