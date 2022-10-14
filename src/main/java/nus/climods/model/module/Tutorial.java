package nus.climods.model.module;

import static java.util.Objects.requireNonNull;
import static nus.climods.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's Tutorial in the List of Modules. Guarantees: immutable
 */

public class Tutorial {
    public static final String MESSAGE_CONSTRAINTS =
            "Tutorial should be in the format example Monday 2pm, and it should not be blank"; //TBC!

    /*
     * The first character of the module tutorial must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String tutorial;

    /**
     * Constructs a {@tutorial Name}.
     *
     * @param tutorial A valid tutorial.
     */
    public Tutorial(String tutorial) {
        requireNonNull(tutorial);
        checkArgument(isValidName(tutorial), MESSAGE_CONSTRAINTS);
        this.tutorial = tutorial;
    }

    /**
     * Returns true if a given string is a valid tutorial.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return tutorial;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tutorial // instanceof handles nulls
                && tutorial.equals(((Tutorial) other).tutorial)); // state check
    }

    @Override
    public int hashCode() {
        return tutorial.hashCode();
    }
}
