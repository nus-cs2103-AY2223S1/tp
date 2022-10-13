package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the deadline of a task in the TaskList.
 * Deadline contains a date but not a time.
 */
public class Deadline {
    public static final String MESSAGE_CONSTRAINTS = "Deadlines should be in the format DD-MM-YYYY";

    /*
     * Date should be in the format DD-MM-YYYY.
     * Validation will be handled by DateTimeFormatter util class.
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MM yyyy");

    private final LocalDate date;

    /**
     * Constructs an {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        //TODO Fix isValidDeadline
        //checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        date = LocalDate.parse(deadline);
    }

    /**
     * Returns if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        // TODO: Try-catch used as control flow, refactor
        try {
            DATE_TIME_FORMATTER.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return date.format(DATE_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && date.equals(((Deadline) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
