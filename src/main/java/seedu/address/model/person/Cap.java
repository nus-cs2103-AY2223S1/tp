package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's CAP (Cumulative Average Point) in the address book.
 * Guarantees: immutable, is valid as declared in
 * {@link #isValidCapValues(double, double)}
 */
public class Cap {
    public static final String MESSAGE_CONSTRAINTS = "CAPs should only consist of two numeric values "
        + "without symbols and alphabets, "
        + "namely the current CAP value and its maximum value!\n"
        + "e.g. 4.0/5.0";
    public static final double MINIMUM = 0.0;
    public static final String CAP_SEPARATOR = "/";
    public static final String VALIDATION_REGEX = "[\\s]*[0-9][\\s]*[.]?[\\s]*[0-9]*[\\s]*";
    public final double value;
    public final double maximum;

    /**
     * Constructs a {@code Cap}.
     *
     * @param cap A valid CAP value.
     * @param max A maximum value for the CAP.
     */
    public Cap(double cap, double max) {
        requireNonNull(cap);
        requireNonNull(max);
        checkArgument(isValidCapValues(cap, max), MESSAGE_CONSTRAINTS);
        String roundedCapValue = String.format("%.2f", cap);
        String roundedMaxValue = String.format("%.2f", max);
        value = Double.parseDouble(roundedCapValue);
        maximum = Double.parseDouble(roundedMaxValue);
    }

    /**
     * Returns true if a given CAP is valid.
     *
     * @param cap A CAP value.
     * @param max A maximum value for the CAP.
     */
    public static boolean isValidCapValues(double cap, double max) {
        return cap <= max && cap > MINIMUM && max <= 100.0;
    }

    /**
     * Returns true if given CAP values are valid, that is containing two valid
     * numeric values only.
     *
     * @param capValues CAP values given.
     */
    public static boolean isValidCapInput(String[] capValues) {
        return capValues.length == 2 && isValidCapFormat(capValues[0]) && isValidCapFormat(capValues[1]);
    }

    /**
     * Returns true if a given CAP value is in a valid format, following the
     * validation regex.
     *
     * @param capFormat CAP format given.
     */
    public static boolean isValidCapFormat(String capFormat) {
        return capFormat.matches(VALIDATION_REGEX);
    }

    public double getValue() {
        return value;
    }

    public double getMaximum() {
        return maximum;
    }

    @Override
    public String toString() {
        String capValue = String.format("%.2f", value);
        String maximumValue = String.format("%.2f", maximum);
        return capValue + CAP_SEPARATOR + maximumValue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Cap
                        && value == ((Cap) other).value
                        && maximum == ((Cap) other).maximum);
    }

    @Override
    public int hashCode() {
        return ((Double) value).hashCode();
    }
}
