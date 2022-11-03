package seedu.address.model.question;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Question's description in the address book.
 * Guarantees: immutable
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS = "Question should not be blank";


    /*
     * The description must contain at least 1 non-space character,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?=\\s*\\S).*$";

    public final String descriptionString;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        descriptionString = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return descriptionString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                        && descriptionString.equals(((Description) other).descriptionString)); // state check
    }

    @Override
    public int hashCode() {
        return descriptionString.hashCode();
    }

}
