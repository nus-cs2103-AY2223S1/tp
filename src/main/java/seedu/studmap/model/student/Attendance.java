package seedu.studmap.model.student;

/**
 * Represents an Attendance object in StudMap.
 * Guarantees: immutable; name is valid as declared in {@link #isValidClassName(String)}
 */
public class Attendance extends MultiStateAttribute<String, Attendance.Status> {

    public static final String MESSAGE_CONSTRAINTS = "Class names should consist of "
            + "alphanumerics, space, dash and underscore.";
    public static final String MESSAGE_STATUS_CONSTRAINTS = "Option must either be 'present' or 'absent' for "
            + "attendance";
    public static final String VALIDATION_REGEX = "[\\p{Alnum} \\-_]+";
    public static final String ATTENDANCE_TRUE = "Present";
    public static final String ATTENDANCE_FALSE = "Absent";

    /**
     * Constructs an {@code Attendance} object.
     *
     * @param className A valid class name.
     * @param status    Attendance status.
     */
    public Attendance(String className, Status status) {
        super(className, status);
    }

    /**
     * Returns true if a given string is a valid class name.
     */
    public static boolean isValidClassName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean isValidAttributeIdentifier(String identifier) {
        return isValidClassName(identifier);
    }

    @Override
    public String getIdentifierConstraintsMessage() {
        return MESSAGE_CONSTRAINTS;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Attendance // instanceof handles nulls
                && identifier.equals(((Attendance) other).identifier)); // identifier check
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    /**
     * Possible states of an Attendance.
     */
    public enum Status {
        PRESENT {
            @Override
            public String toString() {
                return ATTENDANCE_TRUE;
            }
        },
        ABSENT {
            @Override
            public String toString() {
                return ATTENDANCE_FALSE;
            }
        };

        /**
         * Translates a string value of {@code Status} to an enum value.
         */
        public static Status fromString(String value) throws IllegalArgumentException {
            switch (value) {
            case ATTENDANCE_TRUE:
                return PRESENT;
            case ATTENDANCE_FALSE:
                return ABSENT;
            default:
                throw new IllegalArgumentException(MESSAGE_STATUS_CONSTRAINTS);
            }
        }
    }

}
