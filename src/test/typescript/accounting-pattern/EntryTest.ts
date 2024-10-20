import { Entry } from "../../main/typescript/accounting-pattern/Entry";
import { EntryType } from "../../main/typescript/accounting-pattern/EntryType";
import { MonetaryAmount } from "../../main/typescript/accounting-pattern/MonetaryAmount";

describe("Entry", () => {
    it("should have a date and amount", () => {
        const entryDate = new Date();
        const entryType = EntryType.entryType("foo");
        const amount = new MonetaryAmount("USD", 1);
        const entry = new Entry(entryDate, entryType, amount);

        expect(entry.getAmount()).toEqual(amount);
    });
});
