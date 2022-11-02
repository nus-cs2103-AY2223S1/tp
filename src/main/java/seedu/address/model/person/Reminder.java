package seedu.address.model.person;

import static seedu.address.logic.parser.ParserUtil.DATE_FORMAT_PATTERN;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;


/**
 * Represents a Reminder in the address book.
 */
public class Reminder {
    public final String task;
    public final LocalDate date;

    /**
     * Constructs an {@code Reminder}.
     *
     * @param reminder A valid reminder.
     * @param date A valid date to associated to the reminder.
     */
    public Reminder(String reminder, String date) {

        assert Birthday.isValidDate(date) : "date should be in format of 'DD-MM-YYYY'";

        this.task = reminder;
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)
                .withResolverStyle(ResolverStyle.STRICT));

    }

    @Override
    public String toString() {
        return this.task + " by: " + this.date;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Reminder // instanceof handles nulls
                && task.equals(((Reminder) other).task) && date.equals(((Reminder) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }
}

