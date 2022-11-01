package seedu.studmap.model.student;

import static seedu.studmap.commons.util.AppUtil.checkArgument;
import static seedu.studmap.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents an Attendance object in StudMap.
 * Guarantees: immutable; name is valid as declared in {@link #isValidClassName(String)}
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS = "Class names should consist of "
            + "alphanumerics, space, dash and underscore.";
    public static final String VALIDATION_REGEX = "[\\p{Alnum} \\-_]+";
    public static final String ATTENDANCE_TRUE = "Present";
    public static final String ATTENDANCE_FALSE = "Absent";

    public final String className;
    public final boolean hasAttended;

    /**
     * Constructs an {@code Attendance} object.
     *
     * @param className A valid class name.
     * @param hasAttended A boolean representing whether the student has attended or not
     */
    public Attendance(String className, Boolean hasAttended) {
        requireAllNonNull(className, hasAttended);
        checkArgument(isValidClassName(className), MESSAGE_CONSTRAINTS);
        this.className = className;
        this.hasAttended = hasAttended;
    }

    /**
     * Returns true if a given string is a valid class name.
     */
    public static boolean isValidClassName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getString() {
        return className + ':' + getAttendanceString();
    }

    public String getAttendanceString() {
        return (hasAttended ? ATTENDANCE_TRUE : ATTENDANCE_FALSE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Attendance // instanceof handles nulls
                && className.equals(((Attendance) other).className)); // state check
                // no check for hasAttended to ensure only one attendance record per className
    }

    @Override
    public int hashCode() {
        return className.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + getString() + ']';
    }

}
