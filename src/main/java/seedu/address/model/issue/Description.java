package seedu.address.model.issue;



import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.commands.project.AddProjectCommand;

/**
 * Represents an Issue's description.
 */
public class Description {

    /**
     * Represents an empty description.
     */
    public static class EmptyDescription extends Description {
        public static final Description EMPTY_DESCRIPTION = new EmptyDescription();

        private EmptyDescription() {
            super("there's nothing");
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }
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
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String description) {
        return description.matches(VALIDATION_REGEX);
    }

    public boolean isEmpty() {
        return false;
    }

    public String uiRepresentation() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && description.equals(((Description) other).description));
    }
}
