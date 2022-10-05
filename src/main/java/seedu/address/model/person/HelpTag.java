package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class HelpTag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be called help";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String helpTag;

    /**
     * Constructs a {@code HelpTag}.
     *
     * @param helpTag A valid helpTag name.
     */
    public HelpTag(String helpTag) {
        requireNonNull(helpTag);
        checkArgument(isValidHelpTag(helpTag), MESSAGE_CONSTRAINTS);
        this.helpTag= helpTag;
    }

    /**
     * Returns true if a given string is a valid helptag name.
     */
    public static boolean isValidHelpTag(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpTag // instanceof handles nulls
                && helpTag.equals(((HelpTag) other).helpTag)); // state check
    }

    @Override
    public int hashCode() {
        return helpTag.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[help]";
    }
}
