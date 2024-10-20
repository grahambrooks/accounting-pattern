class TemporalCollection<T> {
    private entries: Map<Date, T> = new Map<Date, T>();

    put(date: Date, rule: T): void {
        this.entries.set(date, rule);
    }

    get(when: Date): T | null {
        const sortedDates = Array.from(this.entries.keys()).sort((a, b) => b.getTime() - a.getTime());
        for (const date of sortedDates) {
            if (when >= date) {
                return this.entries.get(date) || null;
            }
        }
        return null;
    }
}
