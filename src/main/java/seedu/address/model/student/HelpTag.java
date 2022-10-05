package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class HelpTag {

    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String helpTag;

    /**
     * Constructs a {@code HelpTag}.
     *
     * @param helpTag A valid tag name.
     */
    public HelpTag(String helpTag) {
        this.helpTag = helpTag;
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
