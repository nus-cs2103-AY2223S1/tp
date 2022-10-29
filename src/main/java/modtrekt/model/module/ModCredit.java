package modtrekt.model.module;

import static java.util.Objects.requireNonNull;
import static modtrekt.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's credit in the module list.
 * Guarantees: immutable; is valid as declared in {@link #isValidCredit(String)}
 */
public class ModCredit {


    public static final String MESSAGE_CONSTRAINTS =
            "Credit should contain only numeric characters up till 2 digits and cannot be negative.";
    public static final String VALIDATION_REGEX = "\\d{1,2}";
    public final String value;

    /**
     * Constructs a {@code Credit}.
     *
     * @param credit A valid module credit.
     */
    public ModCredit(String credit) {
        requireNonNull(credit);
        checkArgument(isValidCredit(credit), MESSAGE_CONSTRAINTS);
        value = credit;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidCredit(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModCredit // instanceof handles nulls
                && value.equals(((ModCredit) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public String getValue() {
        return value;
    }

    public int getIntValue() {
        return Integer.parseInt(value);
    }
}
