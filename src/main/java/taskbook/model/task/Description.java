package taskbook.model.task;

import static java.util.Objects.requireNonNull;

import taskbook.commons.util.AppUtil;

/**
 * Represents a Task's description in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions should only contain ASCII characters and spaces, "
            + "and it should not be blank";

    /*
     * Supports all ASCII characters.
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{ASCII}][\\p{ASCII}'. ]*";

    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        AppUtil.checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX) && test.trim().length() > 0;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && description.equals(((Description) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    /**
     * Searches description for if the input query exists.
     * @param query input query
     * @return true if query exists exactly in description, false otherwise.
     */
    public boolean isQueryInDescription(String query) {
        requireNonNull(query);
        return description.toUpperCase().contains(query.toUpperCase());
    }

    /**
     * Compares this description and the input description to decide alphabetical order.
     * @param other input description.
     * @return 1 if this description is alphabetically first, -1 otherwise.
     */
    public int compareByAlphabeticalTo(Description other) {
        return this.description.compareToIgnoreCase(other.description);
    }
}
