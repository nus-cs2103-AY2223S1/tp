package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's attendance in the address book.
 * Guarantees: immutable; is always valid
 */
public class Attendance {
    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final LocalDate time;

    /**
     * Constructs an {@code Attendance}.
     *
     * @param attendance a description of the attendance.
     */
    public Attendance(String attendance) {
        requireNonNull(attendance);
        this.time = LocalDate.parse(attendance, DTF);
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
        return temp.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
