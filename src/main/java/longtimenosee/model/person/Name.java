package longtimenosee.model.person;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.util.AppUtil.checkArgument;

import java.util.Comparator;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {
    public static final int MAXIMUM_NAME_LENGTH = 60;
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String LENGTH_CONSTRAINTS =
            "Names should not be more than " + MAXIMUM_NAME_LENGTH
                   + " characters in length";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final Comparator<Person> NAME_COMPARATOR = new Comparator<Person>() {
        public int compare(Person p1, Person p2) {
            return p1.getName().fullName.toUpperCase().compareTo(
                    p2.getName().fullName.toUpperCase());
        }
    };

    public static final String SORT_NAME = "name";


    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        checkArgument(isValidLength(name), LENGTH_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is of a valid length.
     * Presumes that the string is a valid name
     * This accounts for the case where the string is empty.
     */
    public static boolean isValidLength(String test) {
        return test.length() <= MAXIMUM_NAME_LENGTH;
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
