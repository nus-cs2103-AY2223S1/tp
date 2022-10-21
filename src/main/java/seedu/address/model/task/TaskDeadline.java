package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Task's deadline in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class TaskDeadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should be in the format of DD/MM/YYYY";
    public static final String MESSAGE_INVALID_DATE =
            "Either the day or month or both is out of range";
    public static final String VALIDATION_REGEX = "[0-9]{2}/[0-9]{2}/[0-9]{4}";
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    public final Date deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public TaskDeadline(String deadline) {
        Date dateDeadline;
        requireNonNull(deadline);
        checkArgument(isInDeadlineFormat(deadline), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDate(deadline), MESSAGE_INVALID_DATE);
        try {
            dateDeadline = new SimpleDateFormat(DATE_FORMAT).parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
            dateDeadline = null;
        }
        this.deadline = dateDeadline;
    }

    /**
     * Returns true if a given string is in dd/MM/yyyy.
     */
    public static boolean isInDeadlineFormat(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            sdf.parse(test);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.format(deadline);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskDeadline // instanceof handles nulls
                && deadline.equals(((TaskDeadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }

}
