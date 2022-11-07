package seedu.uninurse.model.remark;

import static seedu.uninurse.commons.util.AppUtil.checkArgument;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Patient's additional information as remarks.
 * Guarantees: immutable; is valid as declared in {@link #isValidRemark(String)}
 */
public class Remark {
    public static final String MESSAGE_CONSTRAINTS = "Remarks can take any values and should not be blank.";
    /*
     * The first character of the remark must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String remark;

    /**
     * Constructs a Remark.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        requireAllNonNull(remark);
        checkArgument(isValidRemark(remark), MESSAGE_CONSTRAINTS);
        this.remark = remark;
    }

    /**
     * Returns true if a given string is a valid remark.
     */
    public static boolean isValidRemark(String remark) {
        return remark.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return remark;
    }

    @Override
    public String toString() {
        return remark;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && remark.equals(((Remark) other).remark)); // state check
    }

    @Override
    public int hashCode() {
        return remark.hashCode();
    }
}
