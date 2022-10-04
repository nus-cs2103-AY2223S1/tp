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
            "Deadlines should be entered in the format \"2006-01-15 15:04\"";

    //@@author parnikkapore-reused
    // Date format taken from https://github.com/angkl0/ip/blob/master/src/main/java/duke/tasks/Deadline.java#L39
    // and https://github.com/angkl0/ip/blob/master/src/main/java/duke/commands/DeadlineCommand.java#L42
    private static final DateTimeFormatter WRITE_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a 'on' dd/MM/yyyy");
    private static final DateTimeFormatter READ_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public final LocalDateTime deadline;

    /**
     * Constructs a {@code Deadline} from a handwritten description.
     *
     * @param deadlineString A valid deadline written as a string.
     */
    public Deadline(String deadlineString) {
        requireNonNull(deadlineString);
        checkArgument(isValidDeadline(deadlineString), MESSAGE_CONSTRAINTS);

        LocalDateTime newDeadline;
        try {
            newDeadline = LocalDateTime.parse(deadlineString, READ_FORMATTER);
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
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        try {
            LocalDateTime.parse(test, READ_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
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
