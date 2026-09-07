import type { EntryType } from "./EntryType";
import { MonetaryAmount } from "./MonetaryAmount";
import { PostingRule } from "./PostingRule";
import type { Quantity } from "./Quantity";

/** Charges the agreement's rate for every unit of quantity. */
export class MultiplyByRatePostingRule extends PostingRule {
    constructor(type: EntryType) {
        super(type);
    }

    protected calculateAmount(quantity: Quantity, rate: number): MonetaryAmount {
        return new MonetaryAmount("USD", quantity.getValue() * rate);
    }
}
