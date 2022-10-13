package seedu.phu.model.internship;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Internship's remark in the internship book.
 * Guarantees: immutable;
 */
public class Remark {
    public static final String DEFAULT_VALUE = "";

    public final String value;

    /**
     * Constructs an {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
