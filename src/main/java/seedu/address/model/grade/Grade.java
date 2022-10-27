package seedu.address.model.grade;

/**
 * Represents a Grade (which is either Graded or Ungraded for now)
 * Guarantees: details are present and not null, field values are validated and mutable.
 */
public enum Grade {
    GRADED,
    UNGRADED;

    public static final String MESSAGE_CONSTRAINTS = "Grade should either be T or F";

    public static boolean isValidDescription(String trimmedGrade) {
        return trimmedGrade.equals("T") || trimmedGrade.equals("F");
    }
}
