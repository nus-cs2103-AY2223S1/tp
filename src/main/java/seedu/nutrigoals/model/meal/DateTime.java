package seedu.nutrigoals.model.meal;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the date and time a Food was recorded.
 */
public class DateTime {

    public static final String MESSAGE_CONSTRAINTS = "Date should be valid and in the format: yyyy-MM-dd";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");

    private final LocalDateTime dateTime;

    /**
     * Constructs a {@code DateTime} at the current date and time.
     *
     */
    public DateTime() {
        dateTime = LocalDateTime.now();
    }

    /**
     * Constructs a {@code DateTime} at the given date and time.
     * @param dateTime A string representing the date and time.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        this.dateTime = LocalDateTime.parse(dateTime);
    }

    /**
     * Returns true if this {@code DateTime} represents a later time than the given {@code DateTime}.
     * @param otherDateTime The other {@code DateTime} to compare to.
     * @return True if this {@code DateTime} represents a later time than the given {@code DateTime}.
     */
    public boolean isAfter(DateTime otherDateTime) {
        return dateTime.isAfter(otherDateTime.dateTime);
    }

    /**
     * Checks if this {@code DateTime} was recorded on the same day as the given {@code DateTime}.
     * @param otherDateTime The other {@code DateTime} to compare to.
     * @return True if this {@code DateTime} was recorded on the same day as the given {@code DateTime}.
     */
    public boolean isOnSameDay(DateTime otherDateTime) {
        return dateTime.toLocalDate().isEqual(otherDateTime.dateTime.toLocalDate());
    }

    /**
     * Returns true if a given string represents a valid date and time.
     * @return True if the given string represents a valid date and time.
     */
    public static boolean isValidDateTime(String dateTime) {
        try {
            LocalDateTime.parse(dateTime);
            return !dateTime.startsWith("0000");
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns the date of this {@code DateTime}.
     * @return A string representing the date of this {@code DateTime}.
     */
    public String getDate() {
        return dateTime.format(DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DateTime)) {
            return false;
        }
        return dateTime.isEqual(((DateTime) other).dateTime);
    }

    @Override
    public String toString() {
        return dateTime.toString();
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }
}
