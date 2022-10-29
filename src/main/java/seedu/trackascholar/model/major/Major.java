package seedu.trackascholar.model.major;

import static java.util.Objects.requireNonNull;
import static seedu.trackascholar.commons.util.AppUtil.checkArgument;

/**
 * Represents a Major in TrackAScholar.
 * Guarantees: immutable; name is valid as declared in {@link #isValidMajor(String)}
 */
public class Major {

    public static final String MESSAGE_CONSTRAINTS = "Majors should only contain alphanumeric characters and spaces, "
            + "and adhere to the following constraints:\n"
            + "1. Major should not be empty\n"
            + "2. An applicant can only take up at most 2 distinct Majors";
    public static final int MAXIMUM_NUMBER_OF_MAJORS = 2;
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String major;

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

    /**
     * Returns name of major taken by applicant.
     */
    public String getMajorName() {
        return major;
    }

    /**
     * Returns true if both majors have the same name.
     * This defines a weaker notion of equality between two majors.
     */
    public boolean isSameMajor(Major otherMajor) {
        return otherMajor != null
                && otherMajor.getMajorName().equalsIgnoreCase(this.getMajorName());
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
