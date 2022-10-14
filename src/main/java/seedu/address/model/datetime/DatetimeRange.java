package seedu.address.model.datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a start and end datetime in the ModQuik.
 * Guarantees: immutable
 */

public class DatetimeRange {
    public static final String MESSAGE_CONSTRAINTS =
            "Date and time should be in yyyy-MM-dd HH:mm format, e.g. 2022-01-01 08:00";
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final String MESSAGE_INVALID_DURATION = "The start time should be before end time.";
    public final LocalDateTime startDatetime;
    public final LocalDateTime endDatetime;


    /**
     * Constructs a {@code DatetimeRange}.
     *
     * @param startDatetimeString A valid time.
     * @param endDatetimeString A valid time.
     */
    public DatetimeRange(String startDatetimeString, String endDatetimeString) {
        requireNonNull(startDatetimeString);
        requireNonNull(endDatetimeString);
        checkArgument(isValidDatetime(startDatetimeString), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDatetime(endDatetimeString), MESSAGE_CONSTRAINTS);
        this.startDatetime = LocalDateTime.parse(startDatetimeString, DATE_TIME_FORMAT);
        this.endDatetime = LocalDateTime.parse(endDatetimeString, DATE_TIME_FORMAT);
    }

    private static boolean isValidDatetime(String datetimeString) {
        try {
            LocalDateTime.parse(datetimeString, DATE_TIME_FORMAT);
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }


    /**
     * Returns true if given strings are valid as a datetime range.
     *
     * @param datetimeStart Starting datetime
     * @param datetimeEnd Ending datetime
     * @return Whether both strings are valid when taken together as a time range
     */
    public static boolean isValidDatetimeRange(String datetimeStart, String datetimeEnd) {
        // Unideal: does additional work by creating objects to check validity.
        // This is to confirm to the rest of the codebase, where checks are done on a
        // "check for permission" in a static method beforehand.
        if (!isValidDatetime(datetimeStart) || !isValidDatetime(datetimeEnd)) {
            return false;
        }
        LocalDateTime datetimeStartObj = LocalDateTime.parse(datetimeStart, DATE_TIME_FORMAT);
        LocalDateTime datetimeEndObj = LocalDateTime.parse(datetimeEnd, DATE_TIME_FORMAT);
        return !datetimeEndObj.isBefore(datetimeStartObj);
    }


    /**
     * Get formatted start datetime.
     *
     * @return Formatted start datetime
     */
    public String getStartDatetimeFormatted() {
        return startDatetime.format(DATE_TIME_FORMAT);
    }

    /**
     * Get formatted end datetime.
     *
     * @return Formatted end datetime
     */
    public String getEndDatetimeFormatted() {
        return endDatetime.format(DATE_TIME_FORMAT);
    }

    public String toStorageString() {
        // String that uniquely stores info
        return String.format("%s to %s",
                startDatetime.format(DATE_TIME_FORMAT),
                endDatetime.format(DATE_TIME_FORMAT));
    }

    @Override
    public String toString() {
        return String.format("%s to %s", startDatetime, endDatetime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DatetimeRange // instanceof handles nulls
                && startDatetime.equals(((DatetimeRange) other).startDatetime) // state check
                && endDatetime.equals(((DatetimeRange) other).endDatetime)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDatetime, endDatetime);
    }

}
