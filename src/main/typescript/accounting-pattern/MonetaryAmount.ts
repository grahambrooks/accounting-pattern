/**
 * The number of decimal places a currency actually uses. USD and GBP have two,
 * JPY has none. Unknown codes are rejected rather than silently defaulted.
 */
function fractionDigits(currency: string): number {
    let digits: number | undefined;
    try {
        digits = new Intl.NumberFormat("en", { style: "currency", currency })
            .resolvedOptions().maximumFractionDigits;
    } catch {
        throw new Error(`Unknown currency: ${currency}`);
    }
    if (digits === undefined) {
        throw new Error(`Currency has no known scale: ${currency}`);
    }
    return digits;
}

/** Java's String.hashCode, so the two implementations agree on a value. */
function hashString(value: string): number {
    let hash = 0;
    for (let i = 0; i < value.length; i++) {
        hash = (Math.imul(31, hash) + value.charCodeAt(i)) | 0;
    }
    return hash;
}

/**
 * An immutable amount of money in a single currency.
 *
 * The amount is rounded on construction to the decimal places its currency
 * uses, so amounts that represent the same sum of money compare equal and
 * repeated `add` calls cannot accumulate binary floating-point drift.
 */
export class MonetaryAmount {
    static readonly ZERO = new MonetaryAmount("USD", 0);

    private readonly currency: string;
    private readonly amount: number;

    constructor(currencyName: string, amount: number) {
        const scale = 10 ** fractionDigits(currencyName);
        this.currency = currencyName;
        this.amount = Math.round(amount * scale) / scale;
    }

    add(other: MonetaryAmount): MonetaryAmount {
        if (this.currency !== other.currency) {
            throw new Error(
                `Cannot add amounts with different currencies: ${this.currency} != ${other.currency}`);
        }
        return new MonetaryAmount(this.currency, this.amount + other.amount);
    }

    equals(other: MonetaryAmount): boolean {
        return this.currency === other.currency && this.amount === other.amount;
    }

    hashCode(): number {
        return (Math.imul(31, hashString(this.currency)) + this.amount) | 0;
    }

    getCurrency(): string {
        return this.currency;
    }

    getAmount(): number {
        return this.amount;
    }
}
