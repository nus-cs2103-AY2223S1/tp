package seedu.address.model.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Issue's title.
 */
public class Title {

    /**
     * Represents an empty title.
     */
    public static class EmptyTitle extends Title {

        public static final Title EMPTY_TITLE = new EmptyTitle();

        private EmptyTitle() {
            super("empty");
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public String toString() {
            return "";
        }
    }
    public static final String MESSAGE_CONSTRAINTS =
            "Titles can take any values, and it should not be blank";

    /*
     * The first character of issue title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private String title;

    /**
     * Constructs a issue title.
     *
     * @param title A valid issue title.
     */
    public Title(String title) {
        requireNonNull(title);
        checkArgument(isValidTitle(title), MESSAGE_CONSTRAINTS);
        this.title = title;
    }

    /**
     * Returns true if a given string is a valid title.
     */
    public static boolean isValidTitle(String title) {
        return title.matches(VALIDATION_REGEX);
    }

    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns the UI string representation of the issue title.
     */
    public String uiRepresentation(boolean isPinned, String issueId) {
        return this.title + " " + issueId
                + (isPinned ? " \uD83D\uDCCC" : "");
    }

    @Override
    public String toString() {
        return this.title;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Title // instanceof handles nulls
                && title.equals(((Title) other).title));
    }
}
