import { Customer } from "../../main/typescript/accounting-pattern/Customer";
import { ServiceAgreement } from "../../main/typescript/accounting-pattern/ServiceAgreement";
import { EventType } from "../../main/typescript/accounting-pattern/EventType";
import { MultiplyByRatePostingRule } from "../../main/typescript/accounting-pattern/MultiplyByRatePostingRule";
import { AnnualServiceFeeRule } from "./AnnualServiceFeeRule";
import { EntryType } from "../../main/typescript/accounting-pattern/EntryType";
import { Quantity } from "../../main/typescript/accounting-pattern/Quantity";

describe("Acceptance Test", () => {
    it("should pass the acceptance test", () => {
        const effectiveDate = new Date(1999, 9, 1);
        const customer = new Customer("Acme Coffee Makers",
            new ServiceAgreement(10)
                .addPostingRule(EventType.USAGE, new MultiplyByRatePostingRule(EntryType.BASE_USAGE), effectiveDate)
                .addPostingRule(EventType.SERVICE, new AnnualServiceFeeRule(EntryType.SERVICE, 100), effectiveDate)
                .addPostingRule(EventType.SERVICE, new AnnualServiceFeeRule(EntryType.SERVICE, 1000), new Date(2001, 9, 1))
        );

        customer.post(EventType.USAGE, new Date(1999, 11, 1), new Quantity(50));
        customer.post(EventType.SERVICE, new Date(2000, 9, 1), new Quantity(1));

        expect(customer.balance(new Date(2020, 9, 1)).getAmount()).toBe(600);

        customer.post(EventType.USAGE, new Date(1999, 11, 1), new Quantity(50));

        expect(customer.balance(new Date(2020, 9, 1)).getAmount()).toBe(1100);

        customer.post(EventType.SERVICE, new Date(2001, 10, 1), new Quantity(1));
        expect(customer.balance(new Date(2020, 9, 1)).getAmount()).toBe(2100);
    });
});
