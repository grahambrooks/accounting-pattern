package accounting.patterns;

public class Quantity {
    private final long amount;

    public Quantity(long amount) {
        this.amount = amount;
    }

    public long getValue() {
        return amount;
    }
}
