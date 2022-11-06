package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Team's description in the addressbook
 * Guarantees: immutable; is valid as declared in {@link #isValidTeamDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Team descriptions cannot contain either single (') or double quotes (\"), and should not be blank";

    public static final String VALIDATION_REGEX = "^[^'\\s\"][^'\"]+$";
    public static final String NO_DESCRIPTION_STRING = "No description added";
    public static final Description NO_DESCRIPTION = new Description(NO_DESCRIPTION_STRING);
    public static final Description DEFAULT_DESCRIPTION = new Description("A default team created just for you");
    public final String description;

    /**
     * Constructs a {@code Description}
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidTeamDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidTeamDescription(String test) {
        return test.matches(VALIDATION_REGEX);
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

}
