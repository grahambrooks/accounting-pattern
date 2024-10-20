import { PostingRule } from "../../../main/typescript/accounting-pattern/PostingRule";
import { EntryType } from "../../../main/typescript/accounting-pattern/EntryType";
import { Quantity } from "../../../main/typescript/accounting-pattern/Quantity";
import { MonetaryAmount } from "../../../main/typescript/accounting-pattern/MonetaryAmount";

class AnnualServiceFeeRule extends PostingRule {
    private amount: number;

    constructor(service: EntryType, amount: number) {
        super(service);
        this.amount = amount;
    }

    protected calculateAmount(quantity: Quantity, rate: number): MonetaryAmount {
        return new MonetaryAmount("USD", quantity.getValue() * this.amount);
    }
}
