package tuthub.model.tutor;

import static java.util.Objects.requireNonNull;
import static tuthub.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tutor's name in tuthub.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names can only contain letters, hyphens(-) or apostrophes(') between "
                + "first name and last name, and must contain at least 1 word.\n"
                + "E.g: Alex, Jack Alexander, Harry O'Neil, Smith-Jones.";

    /*
     * Name cannot be blank.
     * Regex: 1 compulsory word, optional space, optional ' or -, optional words, no trailing spaces
     * or spaces in front
     */
    public static final String VALIDATION_REGEX = "^(?! )[A-Za-z]+((\\s)?((['-/])?([A-Za-z])+))*(?<! )$";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
