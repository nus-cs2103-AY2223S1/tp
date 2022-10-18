package seedu.address.model.student;

/**
 * Represents a Student's Help Tag if present in the SETA application.
 */
public class HelpTag {

    public static final String HELP = "Help";

    public final boolean toHelp;

    /**
     * Constructs a {@code HelpTag}.
     */
    public HelpTag(boolean bool) {
        this.toHelp = bool;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpTag // instanceof handles nulls
                && toHelp == ((HelpTag) other).toHelp); // state check
    }

    public boolean getBool() {
        return toHelp;
    }
    /**
     * Format state as text for viewing.
     */
    public String toString() {
        if (toHelp) {
            return "[help]";
        } else {
            return "[help not needed]";
        }
    }
}
