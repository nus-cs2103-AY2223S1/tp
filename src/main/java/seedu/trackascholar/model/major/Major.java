package seedu.trackascholar.model.major;

import static java.util.Objects.requireNonNull;
import static seedu.trackascholar.commons.util.AppUtil.checkArgument;

/**
 * Represents a Major in TrackAScholar.
 * Guarantees: immutable; name is valid as declared in {@link #isValidMajor(String)}
 */
public class Major {

    public static final String MESSAGE_CONSTRAINTS = "Majors names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String major;

    /**
     * Constructs a {@code Major}.
     *
     * @param major A valid major name.
     */
    public Major(String major) {
        requireNonNull(major);
        checkArgument(isValidMajor(major), MESSAGE_CONSTRAINTS);
        this.major = major;
    }

    /**
     * Returns true if a given string is a valid major name.
     */
    public static boolean isValidMajor(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Major // instanceof handles nulls
                && major.equals(((Major) other).major)); // state check
    }

    @Override
    public int hashCode() {
        return major.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + major + ']';
    }

}
