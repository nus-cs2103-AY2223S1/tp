package seedu.address.model.pet;

/**
 * Represents the height of a pet.
 */
public class Height implements Comparable<Height> {

    public static final String UNIT = "cm";
    public static final String MESSAGE_USAGE =
            "The height should be in " + UNIT + " and be a non-negative decimal number, such as 22.8";

    private final double value;

    /**
     * Constructs the height object.
     * @param value The height in double floating point number.
     */
    public Height(double value) {
        if (value < 0) {
            this.value = 0;
        } else {
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Height // instanceof handles nulls
                && value == ((Height) other).value); // state check
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

    @Override
    public String toString() {
        return "Height: " + value + " " + UNIT;
    }

    public double getValue() {
        return value;
    }

    @Override
    public int compareTo(Height o) {
        return Double.compare(this.value, o.value);
    }
}
