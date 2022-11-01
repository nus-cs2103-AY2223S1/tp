package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents the deadline of a task in the TaskList.
 * Deadline contains a date but not a time.
 */
public class Deadline implements Comparable<Deadline> {
    public static final String MESSAGE_CONSTRAINTS = "Deadlines should be in the format DD-MM-YYYY";
    public static final String INVALID_DATE = "Deadlines should be a valid date";

    /*
     * Date should be in the format DD-MM-YYYY.
     * Validation will be handled by DateTimeFormatter util class.
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");

    public final String value;
    public final LocalDate date;

    /**
     * Constructs an {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);

        this.value = deadline;
        date = parseDate(deadline);
    }

    /**
     * Parses the deadline string and returns a LocalDate instance if the deadline is a valid date.
     *
     * @param deadline A deadline.
     * @return An instance of LocalDate.
     * @throws DateTimeParseException Thrown if the deadline provided is not a valid date.
     */
    private static LocalDate parseDate(String deadline) throws DateTimeParseException {
        LocalDate parsedDate = LocalDate.parse(deadline,
                DATE_TIME_FORMATTER.withResolverStyle(ResolverStyle.STRICT));

        return parsedDate;
    }

    @Override
    public int compareTo(Deadline otherDeadline) {
        return this.date.compareTo(otherDeadline.date);
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
