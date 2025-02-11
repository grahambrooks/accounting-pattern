import { Customer } from "../../main/typescript/accounting-pattern/Customer";
import { ServiceAgreement } from "../../main/typescript/accounting-pattern/ServiceAgreement";
import { EventType } from "../../main/typescript/accounting-pattern/EventType";
import { EntryType } from "../../main/typescript/accounting-pattern/EntryType";
import { MultiplyByRatePostingRule } from "../../main/typescript/accounting-pattern/MultiplyByRatePostingRule";
import { Quantity } from "../../main/typescript/accounting-pattern/Quantity";
import { MonetaryAmount } from "../../main/typescript/accounting-pattern/MonetaryAmount";
import { Entry } from "../../main/typescript/accounting-pattern/Entry";

describe("Customer", () => {
    it("should create a valid entry when posting an event", () => {
        const effectiveDate = new Date(1999, 9, 1);
        const customer = new Customer("Acme Coffee Makers",
            new ServiceAgreement(10)
                .addPostingRule(EventType.USAGE, new MultiplyByRatePostingRule(EntryType.BASE_USAGE), effectiveDate)
        );

        customer.post(EventType.USAGE, effectiveDate, new Quantity(50));

        const resultingEntry = customer.getEntry(0);
        expect(resultingEntry.getAmount()).toEqual(new MonetaryAmount("USD", 500));
    });

    it("should throw an error when posting an event without a posting rule", () => {
        const effectiveDate = new Date(1999, 9, 1);
        const customer = new Customer("Acme Coffee Makers", new ServiceAgreement(10));

        expect(() => customer.post(EventType.USAGE, effectiveDate, new Quantity(50))).toThrow();
    });

    it("should have a name", () => {
        expect(new Customer("foo", new ServiceAgreement(1)).getName()).toEqual("foo");
    });
});
