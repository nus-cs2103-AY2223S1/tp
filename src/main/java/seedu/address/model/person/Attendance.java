package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's attendance in the address book.
 * Guarantees: immutable; is always valid
 */
public class Attendance {

    public static final String MESSAGE_CONSTRAINTS =
            "Attendance should only be in the format of yyyy-MM-dd.";

    /**
     * The string input must match the format of yyyy-MM-dd.
     */
    public static final String VALIDATION_REGEX = "^(\\d){4}-(\\d){2}-(\\d){2}$";

    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final LocalDate time;

    /**
     * Constructs an {@code Attendance}.
     *
     * @param attendance a description of the attendance.
     */
    public Attendance(String attendance) {
        requireNonNull(attendance);
        checkArgument(isValidAttendance(attendance), MESSAGE_CONSTRAINTS);
        this.time = LocalDate.parse(attendance, DTF);
    }

    /**
     * Returns true if a given string is a valid attendance.
     */
    public static boolean isValidAttendance(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return time.format(DTF);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { //short circuit if same object
            return true;
        } else if (!(other instanceof Attendance)) { //instanceof handles nulls
            return false;
        }
        Attendance temp = (Attendance) other;
        return temp.toString().equalsIgnoreCase(this.toString());
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
