package swift.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import static swift.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's deadline in the address book.
 * Guarantees: immutable; valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadline must be in `dd-MM-yyyy HHmm` format.";

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public final LocalDateTime deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
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

    @Override
    public String toString() {
        return DATE_TIME_FORMATTER.format(deadline);
    }

    @Override
    public boolean equals(Object other) {
        return (this == other)
                || (other instanceof Deadline
                && deadline.equals(((Deadline) other).deadline));
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
