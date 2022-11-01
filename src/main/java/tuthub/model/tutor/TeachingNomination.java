package tuthub.model.tutor;

import static java.util.Objects.requireNonNull;
import static tuthub.commons.util.AppUtil.checkArgument;

/**
 * Represents the number of teaching nominations that a Tutor has.
 * Guarantees: immutable; is valid as declared in {@link #isValidTeachingNomination(String)}
 */
public class TeachingNomination {

    public static final String MESSAGE_CONSTRAINTS = "Teaching Nomination should only contain positive integer values"
            + " including zero with no spaces in between, and it should not be blank";

    /**
     * Covers positive integer values including 0.
     */
    public static final String VALIDATION_REGEX = "^0{1}$|^[1-9]\\d*$";

    public final String value;

    /**
     * Constructs a {@code TeachingNomination}
     *
     * @param noOfTeachingNominations Number of teaching nominations Tutor has.
     */
    public TeachingNomination(String noOfTeachingNominations) {
        requireNonNull(noOfTeachingNominations);
        checkArgument(isValidTeachingNomination(noOfTeachingNominations), MESSAGE_CONSTRAINTS);
        value = noOfTeachingNominations;
    }

    /**
     * Returns true if a given string is a valid TeachingNomination.
     */
    public static boolean isValidTeachingNomination(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TeachingNomination // instanceof handles null
            && value.equals(((TeachingNomination) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
