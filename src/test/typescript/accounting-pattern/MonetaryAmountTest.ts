import { MonetaryAmount } from "../../main/typescript/accounting-pattern/MonetaryAmount";

describe("MonetaryAmount", () => {
    it("should be different if amounts differ", () => {
        const monetaryAmount1 = new MonetaryAmount("USD", 1);
        const monetaryAmount2 = new MonetaryAmount("USD", 2);

        expect(monetaryAmount1.equals(monetaryAmount2)).toBe(false);
    });

    it("should be different if currencies differ", () => {
        const monetaryAmount1 = new MonetaryAmount("USD", 1);
        const monetaryAmount2 = new MonetaryAmount("GBP", 1);

        expect(monetaryAmount1.equals(monetaryAmount2)).toBe(false);
    });

    it("should be the same if currency and value match", () => {
        const monetaryAmount1 = new MonetaryAmount("USD", 1);
        const monetaryAmount2 = new MonetaryAmount("USD", 1);

        expect(monetaryAmount1.equals(monetaryAmount2)).toBe(true);
    });

    it("should have a hashcode that varies by currency and value", () => {
        const monetaryAmount1 = new MonetaryAmount("USD", 1);
        const monetaryAmount2 = new MonetaryAmount("USD", 1);
        const monetaryAmount3 = new MonetaryAmount("GBP", 1);
        const monetaryAmount4 = new MonetaryAmount("USD", 2);

        expect(monetaryAmount1.hashCode()).toBe(monetaryAmount2.hashCode());
        expect(monetaryAmount1.hashCode()).not.toBe(monetaryAmount3.hashCode());
        expect(monetaryAmount1.hashCode()).not.toBe(monetaryAmount4.hashCode());
    });
});
