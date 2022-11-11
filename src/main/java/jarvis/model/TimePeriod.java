package jarvis.model;

import static jarvis.commons.util.AppUtil.checkArgument;
import static jarvis.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a time period in JARVIS.
 */
public class TimePeriod {


    public static final String MESSAGE_CONSTRAINTS = "Start time must be before end time";
    public static final String MESSAGE_CONSTRAINTS_DATE = "Date should be in yyyy-MM-dd format and valid (year, month "
            + "and day should be in the valid range)";
    public static final String MESSAGE_CONSTRAINTS_TIME = "Time should be in HH:mm format and valid (hour and minute "
            + "should be in the valid range)";

    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Start datetime must be before end datetime.
     * @param start Starting datetime.
     * @param end Ending datetime.
     */
    public TimePeriod(LocalDateTime start, LocalDateTime end) {
        requireAllNonNull(start, end);
        checkArgument(isValidTimePeriod(start, end), MESSAGE_CONSTRAINTS);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns true if 2 datetime values represent a valid time period.
     * @param start Starting datetime.
     * @param end Ending datetime.
     * @return True if start is before end, False otherwise.
     */
    public static boolean isValidTimePeriod(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            return false;
        }
        return start.isBefore(end);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public boolean hasOverlap(TimePeriod other) {
        return end.isAfter(other.start) && start.isBefore(other.end);
    }

    /**
     * Returns true if start date and end date falls on the same day.
     */
    public boolean isOnSameDay() {
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();
        return startDate.isEqual(endDate);
    }

    @Override
    public String toString() {
        if (isOnSameDay()) {
            return start.format(DATE_TIME_FORMAT) + " - " + end.format(TIME_FORMAT);
        }
        return start.format(DATE_TIME_FORMAT) + " - " + end.format(DATE_TIME_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TimePeriod)) {
            return false;
        }

        TimePeriod otherTimePeriod = (TimePeriod) other;
        return otherTimePeriod.getStart().equals(getStart())
                && otherTimePeriod.getEnd().equals(getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
