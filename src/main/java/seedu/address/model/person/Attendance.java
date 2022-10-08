package seedu.address.model.person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;

public class Attendance {
    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public LocalDateTime time;

    public Attendance(String attendance) {
        requireNonNull(attendance);
        this.time = LocalDateTime.parse(attendance);
    }

    @Override
    public String toString() {
        return this.time.format(DTF);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof Attendance)) {
            return false;
        }
        Attendance temp = (Attendance) other;
        return temp.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return this.time.hashCode();
    }
}
