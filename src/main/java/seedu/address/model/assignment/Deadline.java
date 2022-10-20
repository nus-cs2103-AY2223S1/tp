package seedu.address.model.assignment;

import java.time.LocalDateTime;

import seedu.address.logic.parser.DateTimeParser;

/**
 * The deadline when a task is due.
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Date must be in the form of yyyy-MM-dd or yyyy-MM-dd HH:mm";

    private LocalDateTime dateTime;

    /**
     * Empty constructor to prevent error reading from jsonFile.
     */
    public Deadline() {};

    public Deadline(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns the String format of datetime after parsing
     * @return String representation of the datetime
     */
    public String getDeadlineString() {
        return DateTimeParser.getDateTimeString(dateTime);
    }

}
