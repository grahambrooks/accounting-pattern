class Entry {
    private entryDate: Date;
    private entryType: EntryType;
    private amount: MonetaryAmount;

    constructor(entryDate: Date, entryType: EntryType, amount: MonetaryAmount) {
        this.entryDate = entryDate;
        this.entryType = entryType;
        this.amount = amount;
    }

    getAmount(): MonetaryAmount {
        return this.amount;
    }

    getEntryDate(): Date {
        return this.entryDate;
    }
}
