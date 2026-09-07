export class Quantity {
    private readonly amount: number;

    constructor(amount: number) {
        if (amount < 0) {
            throw new Error(`Quantity cannot be negative: ${amount}`);
        }
        this.amount = amount;
    }

    getValue(): number {
        return this.amount;
    }
}
