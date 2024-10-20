class MonetaryAmount {
    static ZERO = new MonetaryAmount("USD", 0);
    private currency: string;
    private amount: number;

    constructor(currencyName: string, amount: number) {
        this.currency = currencyName;
        this.amount = amount;
    }

    add(other: MonetaryAmount): MonetaryAmount {
        if (this.currency !== other.currency) {
            throw new Error("Currencies must be the same");
        }
        return new MonetaryAmount(this.currency, this.amount + other.amount);
    }

    equals(other: MonetaryAmount): boolean {
        return this.currency === other.currency && this.amount === other.amount;
    }

    hashCode(): number {
        let result = this.currency.hashCode();
        result = 31 * result + this.amount;
        return result;
    }

    getAmount(): number {
        return this.amount;
    }
}
