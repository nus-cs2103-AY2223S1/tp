package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline.
 */
public class Deadline {

    /**
     * Represents an empty deadline.
     */
    public static class EmptyDeadline extends Deadline {
        public static final Deadline EMPTY_DEADLINE = new EmptyDeadline();

        private EmptyDeadline() {
            super("1900-01-01");
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public String uiRepresentation() {
            return "No Deadline Set";
        }

        @Override
        public String toString() {
            return "";
        }
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should be entered in yyyy-mm-dd format and must exist \n"
            + "Year (yyyy): Year from 0001 onwards \n"
            + "Month (mm): Month in the range of 1 to 12 \n"
            + "Day (dd): Day in the range of 1 to 31";
    /*
     * The date must be entered in yyyy-mm-dd format
     */
    public static final String VALIDATION_REGEX = "^(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";

    private LocalDate deadline;

    /**
     * Constructs a  Deadline.
     *
     * @param deadline A valid  deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        this.deadline = LocalDate.parse(deadline);
    }

    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String deadline) {
        if (deadline.startsWith("0000") || !deadline.matches(VALIDATION_REGEX)) {
            return false;
        }
        try {
            LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public String getFormattedDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String uiRepresentation() {
        return "Due by: " + this.getFormattedDeadline();
    }

    public LocalDate getLocalDate() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return deadline.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && deadline.equals(((Deadline) other).deadline)); // state check
    }
}
