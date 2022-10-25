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
public class DateTime {

    public static final String DATE_TIME_PATTERN = "dd-MM-yyyy HHmm";
    public static final String DATE_PATTERN = "dd-MM-yyyy";
    public static final String TIME_PATTERN = "HH:mm";
    public static final String MESSAGE_CONSTRAINTS = "Date and time should be in the format of: "
            + DATE_TIME_PATTERN + " i.e 16-10-2022 1015\n" + "or just date should be in the format of: "
            + DATE_PATTERN + " i.e 20-10-2022";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);

    public final LocalDateTime dateTime;


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
     * returns if the Date is today's date.
     */
    public boolean isToday() {
        LocalDateTime today = LocalDateTime.now();

        return today.getDayOfYear() == dateTime.getDayOfYear()
                && today.getYear() == dateTime.getYear();
    }

    public int findDaysFromToday() {
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
}
