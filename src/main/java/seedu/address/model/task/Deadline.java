package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task's deadline.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 *
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "The deadline was not understood. Perhaps enter it like \"2 Jan 2022 15:04\" or \"tomorrow 2pm\"?";

    //@@author parnikkapore-reused
    // Date format taken from https://github.com/angkl0/ip/blob/master/src/main/java/duke/tasks/Deadline.java#L39
    // and https://github.com/angkl0/ip/blob/master/src/main/java/duke/commands/DeadlineCommand.java#L42
    private static final DateTimeFormatter WRITE_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a 'on' dd/MM/yyyy");
    private static final DateTimeFormatter CONSTRUCT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public final LocalDateTime deadline;

    /**
     * Constructs a {@code Deadline} from a String written in the CONSTRUCT_FORMATTER format, such as that
     * returned by deadlineString().
     *
     * @param deadlineString A valid deadline string.
     */
    public Deadline(String deadlineString) {
        requireNonNull(deadlineString);
        checkArgument(isValidDeadline(deadlineString), MESSAGE_CONSTRAINTS);

        LocalDateTime newDeadline;
        try {
            newDeadline = LocalDateTime.parse(deadlineString, CONSTRUCT_FORMATTER);
        } catch (DateTimeParseException e) {
            assert false : "Checked deadline string should not fail to parse?";
            newDeadline = LocalDateTime.now(); // A good enough fallback
        }

        this.deadline = newDeadline;
    }

    /**
     * Constructs a {@code Deadline} from a LocalDateTime.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(LocalDateTime deadline) {
        requireNonNull(deadline);
        this.deadline = deadline;
    }

    /**
     * Returns true if a given string is a valid deadline string.
     */
    public static boolean isValidDeadline(String test) {
        try {
            LocalDateTime.parse(test, CONSTRUCT_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    /**
     * Returns a string representation of this Deadline in a format that can be reimported using
     * Deadline(String).
     *
     * @return The string representation.
     */
    public String deadlineString() {
        return deadline.format(CONSTRUCT_FORMATTER);
    }

    @Override
    public String toString() {
        return deadline.format(WRITE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && deadline.equals(((Deadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
