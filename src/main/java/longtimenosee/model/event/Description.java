package longtimenosee.model.event;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's description in the address book. Also serves as the title
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final int MAXIMUM_DESCRIPTION_LENGTH = 175;
    public static final String MESSAGE_CONSTRAINTS = "Description should only contain "
        + "alphanumeric characters and spaces. It should not be empty";
    public static final String LENGTH_CONSTRAINTS = "Ensure description is of an appropriate length";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input, similar to Name.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private String description;
    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid name.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        checkArgument(isValidLength(description), LENGTH_CONSTRAINTS);
        this.description = description;
    }
    /**
     * Returns true if a given string is a valid description
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true is a given string is of a valid length
     * Presumes that the description is already valid.
     * This is to account for the case where the description is empty.
     */
    public static boolean isValidLength(String test) {
        assert(isValidDescription(test));
        return test.length() <= MAXIMUM_DESCRIPTION_LENGTH;
    }
    @Override
    public String toString() {
        return description;
    }
    public String retrieveDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && description.equals(((Description) other).description)); //state check
    }

}
