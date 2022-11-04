package seedu.uninurse.model.task;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents Date & Time encapsulation for tasks
 */
public class DateTime implements Comparable<DateTime> {

    public static final String DATE_TIME_PATTERN = "d-M-yy HHmm";
    public static final String DATE_PATTERN = "d-M-yy";
    public static final String TIME_PATTERN = "h:mm a";
    public static final String MESSAGE_CONSTRAINTS = "Date and time should be in the format of: "
            + DATE_TIME_PATTERN + " i.e 16-10-22 1015\n" + "or just date should be in the format of: "
            + DATE_PATTERN + " i.e 20-10-22";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);

    private final LocalDateTime dateTime;


    /**
     * Constructs a default Date and Time, one day from the current time.
     */
    public DateTime() {
        dateTime = LocalDateTime.now().plusDays(1);
    }

    /**
     * Constructs a {@code DateTime} with the given {@code validDateTime}.
     */
    public DateTime(String validDateTime) {
        requireNonNull(validDateTime);
        assert(isValidDateTime(validDateTime));
        dateTime = LocalDateTime.parse(validDateTime, DATE_TIME_FORMATTER);
    }

    /**
     * Constructs a {@code DateTime} with the given {@code localDateTime}.
     */
    public DateTime(LocalDateTime localDateTime) {
        requireNonNull(localDateTime);
        dateTime = localDateTime;
    }

    /**
     * returns if the Date is today's date.
     */
    public boolean isToday() {
        LocalDateTime today = LocalDateTime.now();

        return today.getDayOfYear() == dateTime.getDayOfYear()
                && today.getYear() == dateTime.getYear();
    }

    public boolean isPastDate() {
        return dateTime.isBefore(LocalDateTime.now());
    }

    public boolean isBefore(DateTime dateTimeToCheck) {
        return dateTime.isBefore(dateTimeToCheck.dateTime);
    }

    /**
     * Returns the amount of days between today and the Date.
     */
    public int getDaysFromToday() {
        Long daysBetween = DAYS.between(LocalDate.now(), this.dateTime.toLocalDate());
        return daysBetween.intValue();
    }

    /**
     * returns if the {@code test} is a valid date time string.
     */
    public static boolean isValidDateTime(String test) {
        requireNonNull(test);
        try {
            DATE_TIME_FORMATTER.parse(test);
            return true;
        } catch (DateTimeParseException dtpe) {
            return false;
        }
    }

    /**
     * returns if the {@code test} is a valid date string.
     */
    public static boolean isValidDate(String test) {
        requireNonNull(test);
        try {
            DATE_FORMATTER.parse(test);
            return true;
        } catch (DateTimeParseException dtpe) {
            return false;
        }
    }

    /**
     * Returns a {@code DateTime} that is {@code freq} many {@code Recurrence} from
     * this {@code DateTime}.
     */
    public DateTime plusDuration(Recurrence recur, int freq) {
        switch (recur) {
        case DAY:
        case DAYS:
            return new DateTime(dateTime.plusDays(freq));
        case WEEK:
        case WEEKS:
            return new DateTime(dateTime.plusWeeks(freq));
        case MONTH:
        case MONTHS:
            return new DateTime(dateTime.plusMonths(freq));
        case YEAR:
        case YEARS:
            return new DateTime(dateTime.plusYears(freq));
        default:
            return new DateTime();
        }
    }

    /**
     * Factory method to get a {@code DateTime} with {@code validDate} only, the time
     * field defaults to 0000 hours.
     */
    public static DateTime ofDate(String validDate) {
        requireNonNull(validDate);
        assert(isValidDate(validDate));
        return new DateTime(validDate + " 0000");
    }

    /**
     * returns if the date portion is the given date to check.
     */
    public boolean isDate(DateTime dateToCheck) {
        return this.dateTime.toLocalDate().equals(dateToCheck.dateTime.toLocalDate());
    }

    public String getDate() {
        return dateTime.toLocalDate().toString();
    }

    public String getTime() {
        return dateTime.toLocalTime().format(TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return dateTime.format(DATE_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DateTime)) {
            return false;
        }

        DateTime o = (DateTime) other;

        return this.dateTime.getYear() == o.dateTime.getYear()
                && this.dateTime.getDayOfYear() == o.dateTime.getDayOfYear()
                && this.dateTime.getHour() == o.dateTime.getHour()
                && this.dateTime.getMinute() == o.dateTime.getMinute();
    }

    @Override
    public int compareTo(DateTime o) {
        // Special check for equality since we do not want any time period smaller than a minute affecting equality.
        if (this.equals(o)) {
            return 0;
        }

        return dateTime.isBefore(o.dateTime) ? -1 : 1;
    }

}
