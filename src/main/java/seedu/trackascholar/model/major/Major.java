package seedu.trackascholar.model.major;

import static java.util.Objects.requireNonNull;
import static seedu.trackascholar.commons.util.AppUtil.checkArgument;

/**
 * Represents a Major in TrackAScholar.
 * Guarantees: immutable; name is valid as declared in {@link #isValidMajorName(String)}
 */
public class Major {

    public static final String MESSAGE_CONSTRAINTS = "Majors names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String majorName;

    /**
     * Constructs a {@code Major}.
     *
     * @param majorName A valid major name.
     */
    public Major(String majorName) {
        requireNonNull(majorName);
        checkArgument(isValidMajorName(majorName), MESSAGE_CONSTRAINTS);
        this.majorName = majorName;
    }

    /**
     * Returns true if a given string is a valid major name.
     */
    public static boolean isValidMajorName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Major // instanceof handles nulls
                && majorName.equals(((Major) other).majorName)); // state check
    }

    @Override
    public int hashCode() {
        return majorName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + majorName + ']';
    }

}
