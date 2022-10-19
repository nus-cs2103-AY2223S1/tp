package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Person's attendance in the address book.
 * Guarantees: immutable; is always valid
 */
public class Attendance {

    /**
     * Feedback message to User is yyyy as it is more understandable as year than uuuu.
     */
    public static final String MESSAGE_CONSTRAINTS =
            "Attendance should only be in the format of yyyy-MM-dd.";

    public static final String MESSAGE_INVALID_DATE =
            "Attendance should only be of valid dates!";

    /**
     * The string input must match the format of uuuu-MM-dd.
     */
    public static final String VALIDATION_REGEX = "^(\\d){4}-(\\d){2}-(\\d){2}$";

    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    public final LocalDate time;

    /**
     * Constructs an {@code Attendance}.
     *
     * @param attendance a description of the attendance.
     */
    public Attendance(String attendance) {
        requireNonNull(attendance);
        checkArgument(isValidAttendance(attendance), MESSAGE_CONSTRAINTS);
        try {
            this.time = LocalDate.parse(attendance, DTF.withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException dpe) {
            throw new IllegalArgumentException(MESSAGE_INVALID_DATE);
        }
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
