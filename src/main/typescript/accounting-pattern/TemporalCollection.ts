/**
 * A collection of values indexed by the date they took effect. `get` answers
 * with the value that was in effect on a given date.
 */
export class TemporalCollection<T> {
    // Keyed by time value rather than by Date, which a Map compares by identity:
    // two Dates for the same instant are different keys.
    private readonly entries = new Map<number, T>();

    put(date: Date, value: T): void {
        this.entries.set(date.getTime(), value);
    }

    get(when: Date): T | null {
        const asAt = when.getTime();
        let effectiveFrom = Number.NEGATIVE_INFINITY;
        let effective: T | null = null;

        for (const [date, value] of this.entries) {
            if (date <= asAt && date > effectiveFrom) {
                effectiveFrom = date;
                effective = value;
            }
        }
        return effective;
    }
}
