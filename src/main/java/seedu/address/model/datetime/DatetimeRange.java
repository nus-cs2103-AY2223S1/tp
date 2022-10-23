package seedu.address.model.datetime;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.datetime.DatetimeCommonUtils.DATETIME_FORMAT;
import static seedu.address.model.datetime.DatetimeCommonUtils.DATETIME_FORMATTER;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


/**
 * Represents a start and end datetime in the ModQuik.
 * Guarantees: immutable
 */

public class DatetimeRange {
    public final LocalDateTime startDatetime;
    public final LocalDateTime endDatetime;



    /**
     * Constructs a {@code DatetimeRange}.
     *
     * @param startDatetimeString A valid time.
     * @param endDatetimeString A valid time.
     */
    public DatetimeRange(LocalDateTime startDatetime, LocalDateTime endDatetime) {
        requireNonNull(startDatetime);
        requireNonNull(endDatetime);
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    public static DatetimeRange fromFormattedString(String dateString, String startTimeString, String endTimeString) {
        LocalTime startTime = LocalTime.parse(startTimeString, DatetimeCommonUtils.TIME_FORMATTER);
        LocalTime endTime = LocalTime.parse(endTimeString, DatetimeCommonUtils.TIME_FORMATTER);
        LocalDateTime startDatetime = LocalDate.parse(dateString, DatetimeCommonUtils.DATE_FORMATTER)
                .atTime(startTime);
        LocalDateTime endDatetime = LocalDate.parse(dateString, DatetimeCommonUtils.DATE_FORMATTER)
                .atTime(endTime);
        return new DatetimeRange(startDatetime, endDatetime);
    }

    public static DatetimeRange fromFormattedString(String startDatetimeString, String endDatetimeString) {
        LocalDateTime startDatetime = LocalDateTime.parse(startDatetimeString, DATETIME_FORMATTER);
        LocalDateTime endDatetime = LocalDateTime.parse(endDatetimeString, DATETIME_FORMATTER);
        return new DatetimeRange(startDatetime, endDatetime);
    }


    /**
     * Get formatted start datetime.
     *
     * @return Formatted start datetime
     */
    public String getStartDatetimeFormatted() {
        return startDatetime.format(DATETIME_FORMATTER);
    }

    /**
     * Get formatted end datetime.
     *
     * @return Formatted end datetime
     */
    public String getEndDatetimeFormatted() {
        return endDatetime.format(DATETIME_FORMATTER);
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
