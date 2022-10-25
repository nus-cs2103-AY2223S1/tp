package swift.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static swift.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's deadline in the address book.
 * Guarantees: immutable; may contain null; if not null, then valid as declared
 * in {@link #isValidDeadline(String)}
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines must be in `dd-MM-yyyy HHmm` format.";

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public final LocalDateTime deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline, which may be null.
     */
    public Deadline(String deadline) {
        if (deadline == null) {
            this.deadline = null;
            return;
        }
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        this.deadline = LocalDateTime.parse(deadline, DATE_TIME_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        try {
            LocalDateTime.parse(test, DATE_TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if this deadline contains a null value.
     */
    public boolean containsNull() {
        return deadline == null;
    }

    @Override
    public String toString() {
        if (deadline == null) {
            return "";
        }
        return DATE_TIME_FORMATTER.format(deadline);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;
            if (deadline == null && otherDeadline.deadline == null) {
                return true;
            }
            if (deadline == null) {
                return false;
            }
            return deadline.equals(otherDeadline.deadline);
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (deadline == null) {
            return 0;
        }
        return deadline.hashCode();
    }
}
