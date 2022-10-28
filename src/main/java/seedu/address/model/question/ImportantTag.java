package seedu.address.model.question;
/**
 * Represents a Student's Help Tag if present in the SETA application.
 */
public class ImportantTag {

    public static final String IMPORTANT = "Important";

    public final boolean isImportant;

    /**
     * Constructs a {@code ImportantTag}.
     */
    public ImportantTag(boolean bool) {
        this.isImportant = bool;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportantTag // instanceof handles nulls
                && isImportant == ((ImportantTag) other).isImportant); // state check
    }

    public boolean getBool() {
        return isImportant;
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        if (isImportant) {
            return "Important";
        } else {
            return "Not important";
        }
    }
}
