package seedu.address.model.reminder;

/**
 * Represents a date and time.
 */
public class DateTime {
    private final String dateTime;

    public DateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns true if a given string is a valid date time.
     */
    public static boolean isValidDateTime(String dateTime) {
        return true;
    }

    public String getDateTimeString() {
        return this.dateTime;
    }
}
