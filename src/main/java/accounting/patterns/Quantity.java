package accounting.patterns;

/**
 * Represents a quantity value in the accounting system.
 * This record is immutable and thread-safe.
 */
public record Quantity(long value) {
    /**
     * Creates a new Quantity with validation.
     *
     * @param value the quantity value
     * @throws IllegalArgumentException if value is negative
     */
    public Quantity {
        if (value < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative: " + value);
        }
    }

    /**
     * Creates a zero quantity.
     *
     * @return a quantity with value zero
     */
    public static Quantity zero() {
        return new Quantity(0);
    }

    /**
     * Creates a quantity of one.
     *
     * @return a quantity with value one
     */
    public static Quantity one() {
        return new Quantity(1);
    }

    /**
     * Creates a new quantity with the specified value.
     *
     * @param value the quantity value
     * @return a new Quantity instance
     * @throws IllegalArgumentException if value is negative
     */
    public static Quantity of(long value) {
        return new Quantity(value);
    }

    /**
     * Adds another quantity to this one.
     *
     * @param other the quantity to add
     * @return a new Quantity with the sum
     * @throws NullPointerException if other is null
     * @throws ArithmeticException if the result would overflow
     */
    public Quantity add(Quantity other) {
        if (other == null) {
            throw new NullPointerException("Cannot add null quantity");
        }
        long result = Math.addExact(this.value, other.value);
        return new Quantity(result);
    }

    /**
     * Multiplies this quantity by a factor.
     *
     * @param factor the factor to multiply by
     * @return a new Quantity with the product
     * @throws ArithmeticException if the result would overflow
     */
    public Quantity multiply(long factor) {
        if (factor < 0) {
            throw new IllegalArgumentException("Multiplication factor cannot be negative: " + factor);
        }
        long result = Math.multiplyExact(this.value, factor);
        return new Quantity(result);
    }

    /**
     * Checks if this quantity is zero.
     *
     * @return true if the quantity is zero, false otherwise
     */
    public boolean isZero() {
        return value == 0;
    }
}
