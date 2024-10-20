import { TemporalCollection } from "../../main/typescript/accounting-pattern/TemporalCollection";

describe("TemporalCollection", () => {
    it("should return null when empty", () => {
        const collection = new TemporalCollection<string>();
        expect(collection.get(new Date())).toBeNull();
    });

    it("should return null if none valid", () => {
        const collection = new TemporalCollection<string>();
        collection.put(new Date(Date.now() + 86400000), "foo"); // 1 day in the future
        expect(collection.get(new Date())).toBeNull();
    });

    it("should return value if in range", () => {
        const collection = new TemporalCollection<string>();
        collection.put(new Date(Date.now() - 86400000), "foo"); // 1 day in the past
        expect(collection.get(new Date())).toBe("foo");
    });

    it("should return most recent", () => {
        const collection = new TemporalCollection<string>();
        collection.put(new Date(Date.now() - 172800000), "Day before Yesterday"); // 2 days in the past
        collection.put(new Date(Date.now() - 86400000), "Yesterday"); // 1 day in the past
        expect(collection.get(new Date())).toBe("Yesterday");
    });

    it("should return for same day", () => {
        const collection = new TemporalCollection<string>();
        const yesterday = new Date(Date.now() - 86400000); // 1 day in the past
        collection.put(yesterday, "Yesterday");
        expect(collection.get(yesterday)).toBe("Yesterday");
    });
});
