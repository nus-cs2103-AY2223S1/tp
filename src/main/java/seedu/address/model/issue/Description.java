package seedu.address.model.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Issue's description.
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions can take any values, and it should not be blank";

    /*
     * The first character of issue description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private String description;

    /**
     * Constructs a issue description.
     *
     * @param description A valid issue description.
     */
    public Description (String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public boolean isValidDescription(String test) {
        return description.matches(VALIDATION_REGEX);
    }
}
