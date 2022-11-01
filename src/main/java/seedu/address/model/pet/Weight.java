package seedu.address.model.pet;

/**
 * A class the represents the weight of a pet.
 */
public class Weight implements Comparable<Weight> {

    public static final String UNIT = "kg";
    public static final String MESSAGE_USAGE =
            "The weight should be in " + UNIT + " and be a non-negative decimal number, such as 23.8";

    private final double value;

    /**
     * Constructs the weight object.
     * @param value The weight in double.
     */
    public Weight(double value) {
        if (value < 0) {
            this.value = 0;
        } else {
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Weight // instanceof handles nulls
                && value == ((Weight) other).value); // state check
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    @Override
    public String toString() {
        return "Weight: " + value + " " + UNIT;
    }

    public double getValue() {
        return value;
    }

    @Override
    public int compareTo(Weight o) {
        return Double.compare(this.value, o.value);
    }
}
