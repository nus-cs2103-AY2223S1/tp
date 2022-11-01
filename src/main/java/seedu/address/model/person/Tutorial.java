package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's tutorial group in uNivUSal
 */
public class Tutorial {

    public static final String MESSAGE_CONSTRAINTS = "Tutorial should only start with a letter from [W/T/F] followed, "
            + "by two digits. For example: T08";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[W/T/F]\\d{2}";

    public final String tut;

    /**
     * Constructs a {@code Tutorial}.
     *
     * @param tutorial A valid tutorial group.
     */
    public Tutorial(String tutorial) {
        requireNonNull(tutorial);
        checkArgument(isValidTutorial(tutorial), MESSAGE_CONSTRAINTS);
        this.tut = tutorial;
    }

    /**
     * Returns true if input is a valid Tutorial.
     *
     * @param test Input to be tested.
     * @return True for valid input.
     */
    public static boolean isValidTutorial(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return tut;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tutorial // instanceof handles nulls
                && tut.equals(((Tutorial) other).tut)); // state check
    }

    @Override
    public int hashCode() {
        return tut.hashCode();
    }

}
