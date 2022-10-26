package taskbook.model.person;

import static java.util.Objects.requireNonNull;

import taskbook.commons.util.AppUtil;


/**
 * Represents a Person's name in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final Name SELF = new Name("Myself");

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        AppUtil.checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Searches the fullName for a query.
     * @param query input query.
     * @return true if the query exists exactly in fullName, false otherwise.
     */
    public boolean isQueryInName(String query) {
        requireNonNull(query);
        return fullName.toUpperCase().contains(query.toUpperCase());
    }

    /**
     * Compares this person's name and the other person's name to decide name alphabetical order.
     * @param other input person.
     * @return 1 if this person's name is alphabetically first, -1 otherwise.
     */
    public int compareByAlphabeticalTo(Name other) {
        return this.fullName.compareTo(other.fullName);
    }

    /**
     * Compares this person's name and the other person's name to decide name reverse alphabetical order.
     * @param other input person.
     * @return -1 if this person's name is alphabetically first, 1 otherwise.
     */
    public int compareByReverseAlphabeticalTo(Name other) {
        return -1 * this.fullName.compareTo(other.fullName);
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
