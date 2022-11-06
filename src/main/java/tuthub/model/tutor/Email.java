package tuthub.model.tutor;

import static java.util.Objects.requireNonNull;
import static tuthub.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tutor's email in tuthub.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the form E1234567 "
        + "or E1234567@u.nus.edu (case insensitive).";
    public static final String VALIDATION_REGEX = "[e|E][0-9]{7}(@u.nus.edu)?";

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        value = email.contains("@u.nus.edu") ? email : email + "@u.nus.edu";
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && value.equals(((Email) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
