package seedu.address.model.pet;

public class Weight {

    public static final String UNIT = "kg";

    private final double value;

    public Weight(double value) {
        this.value = value;
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
        return value + " " + UNIT;
    }

    public double getValue() {
        return value;
    }

}
