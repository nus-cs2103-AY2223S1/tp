package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        this.task = reminder;
        LocalDate tempDate;
        try {
            tempDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d-MM-yyyy"));
        } catch (DateTimeParseException e) {
            try {
                tempDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-d"));
            } catch (DateTimeParseException e1) {
                throw new RuntimeException("Incorrect Date format given to constructor");
            }
        }
        this.date = tempDate;
    }

//    /**
//     * Constructs an {@code Reminder}.
//     *
//     * @param reminder A valid reminder.
//     */
//    public Reminder(String reminder) {
//        this.task = reminder;
//        this.date = "";
//    }

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

