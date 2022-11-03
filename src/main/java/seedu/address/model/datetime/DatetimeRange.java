package seedu.address.model.datetime;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.datetime.DatetimeCommonUtils.DATETIME_INPUT_FORMATTER;
import static seedu.address.model.datetime.DatetimeCommonUtils.DATE_READABLE_FORMATTER;
import static seedu.address.model.datetime.DatetimeCommonUtils.TIME_FORMATTER;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;


/**
 * Represents a start and end datetime in ModQuik.
 * Guarantees: immutable
 */
public class DatetimeRange {
    public final LocalDateTime startDatetime;
    public final LocalDateTime endDatetime;

    /**
     * Constructs a {@code DatetimeRange}.
     *
     * @param startDatetime A valid time.
     * @param endDatetime A valid time.
     */
    public DatetimeRange(LocalDateTime startDatetime, LocalDateTime endDatetime) {
        requireNonNull(startDatetime);
        requireNonNull(endDatetime);
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    /**
     * Creates a DatetimeRange from formatted strings, when start date and end date is same.
     * Does not do any validation on input, may throw a DateTimeParseException
     *
     * @param dateString The formatted date of both start and end
     * @param startTimeString A formatted time representing start
     * @param endTimeString A formatted time representing start
     * @return A DatetimeRange
     */
    public static DatetimeRange fromFormattedString(String dateString, String startTimeString, String endTimeString) {
        LocalTime startTime = LocalTime.parse(startTimeString, TIME_FORMATTER);
        LocalTime endTime = LocalTime.parse(endTimeString, TIME_FORMATTER);
        LocalDateTime startDatetime = LocalDate.parse(dateString, DatetimeCommonUtils.DATE_INPUT_FORMATTER)
                .atTime(startTime);
        LocalDateTime endDatetime = LocalDate.parse(dateString, DatetimeCommonUtils.DATE_INPUT_FORMATTER)
                .atTime(endTime);
        return new DatetimeRange(startDatetime, endDatetime);
    }

    /**
     * Creates a DatetimeRange from formatted strings.
     * Does not do any validation on input, may throw a DateTimeParseException
     *
     * @param startDatetimeString A formatted datetime representing start
     * @param endDatetimeString A formatted datetime representing end
     * @return A DatetimeRange
     */
    public static DatetimeRange fromFormattedString(String startDatetimeString, String endDatetimeString) {
        LocalDateTime startDatetime = LocalDateTime.parse(startDatetimeString, DATETIME_INPUT_FORMATTER);
        LocalDateTime endDatetime = LocalDateTime.parse(endDatetimeString, DATETIME_INPUT_FORMATTER);
        return new DatetimeRange(startDatetime, endDatetime);
    }


    /**
     * Get formatted start datetime.
     *
     * @return Formatted start datetime
     */
    public String getStartDatetimeFormatted() {
        return startDatetime.format(DATETIME_INPUT_FORMATTER);
    }

    /**
     * Get formatted end datetime.
     *
     * @return Formatted end datetime
     */
    public String getEndDatetimeFormatted() {
        return endDatetime.format(DATETIME_INPUT_FORMATTER);
    }

    /**
     * Converts this DatetimeRange into a human-readable form.
     *
     * @return Human-readable representation of the DatetimeRange
     */
    @Override
    public String toString() {
        if (!startDatetime.toLocalDate().equals(endDatetime.toLocalDate())) {
            return String.format("%s to %s", startDatetime, endDatetime);
        }
        // If start date and end date is same, no need to show it twice
        return String.format("%s, %s to %s",
                startDatetime.toLocalDate().format(DATE_READABLE_FORMATTER),
                startDatetime.toLocalTime().format(TIME_FORMATTER),
                endDatetime.toLocalTime().format(TIME_FORMATTER));
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
