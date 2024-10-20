class Quantity {
    private amount: number;

    constructor(amount: number) {
        this.amount = amount;
    }

    getValue(): number {
        return this.amount;
    }
}
