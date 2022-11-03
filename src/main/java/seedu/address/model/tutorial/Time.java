package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the group number of a tutorial in SETA
 */
public class Time {

    public static final String MESSAGE_CONSTRAINTS =
            "time should only take certain format, and it should not be blank";

    public static final String VALIDATION_PATTERN = "yyyy-MM-dd HHmm";

    public static final String TIME_MEMORY_PATTERN = "HHmm";

    public static final String TIME_UI_PATTERN = "h:mma";
    public static final String DATE_UI_PATTERN = "dd MMM yyyy";

    public final LocalDateTime dateTime;

    /**
     * Constructs a {@code time}.
     *
     * @param dateTime A valid time.
     */
    public Time(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(VALIDATION_PATTERN);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        this.dateTime = localDateTime;
    }

    /**
     * Returns date in a format that will be used in the Ui.
     */
    public String getUiStringDate() {
        return dateTime.toLocalDate().format(DateTimeFormatter.ofPattern(DATE_UI_PATTERN));
    }

    /**
     * Returns time in a format that will be used in the Ui.
     */
    public String getUiStringTime() {
        return dateTime.toLocalTime().format(DateTimeFormatter.ofPattern(TIME_UI_PATTERN));
    }

    /**
     * Returns true if a given string is a valid date and time.
     */
    public static boolean isValidDateTime(String dateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(VALIDATION_PATTERN);
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getUiStringDate() + " " + getUiStringTime();
    }

    /**
     * Returns date and time in a format that will be saved to {@code addressbook.json}.
     */
    public String toMemoryString() {
        return dateTime.toLocalDate()
                + " "
                + dateTime.toLocalTime().format(DateTimeFormatter.ofPattern(TIME_MEMORY_PATTERN));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && dateTime.equals(((Time) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }
}
