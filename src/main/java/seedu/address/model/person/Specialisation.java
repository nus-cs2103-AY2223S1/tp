package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a field that a Professor specialises in.
 * Guarantees: immutable; is valid as declared in {@link #isValidSpecialisation(String)}
 */
public class Specialisation {

    public static final String MESSAGE_CONSTRAINTS =
            "Specialisation can take any values, and it should not be blank.\n"
            + "However when you are editing Specialisation of an existing contact, it can be blank.";

    /*
     * The first character of the specialisation must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final String EMPTY_SPECIALISATION = "";

    public final String value;

    /**
     * Constructs a {@code Specialisation}.
     *
     * @param field A valid specialisation field.
     */
    public Specialisation(String field) {
        requireNonNull(field);
        checkArgument(isValidSpecialisation(field), MESSAGE_CONSTRAINTS);
        value = field;
    }

    /**
     * @param field A valid specialisation field.
     * @param isPresent Whether prefix was present in user input.
     */
    public Specialisation(String field, boolean isPresent) {
        if (isPresent) {
            checkArgument(isValidSpecialisation(field), MESSAGE_CONSTRAINTS);
            value = field;
        } else {
            value = EMPTY_SPECIALISATION;
        }
    }

    /**
     * Returns true if a given string is a valid module code.
     */
    public static boolean isValidSpecialisation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Specialisation // instanceof handles nulls
                && value.equals(((Specialisation) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
