package soconnect.model.todo;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Represents a {code Todo}'s date in the {@code TodoList}.
 * Guarantees: is valid as declared in {@link #isValidDate(String)}.
 */
public class Date {
    public static final String MESSAGE_CONSTRAINS = "Date should be of the format dd-MM-yyyy "
            + "and it should be a valid date.";

    public static final String MESSAGE_INVALID_DATE_RANGE = MESSAGE_CONSTRAINS + "\n"
            + "Date range should be of the format "
            + "dd-MM-yyyy to dd-MM-yyyy.\n"
            + "For date range, the first date should not be later than the second date.";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    private static final DateTimeFormatter DATE_DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public final LocalDate date;

    /**
     * Constructs a {@code Date} with a given {@code String}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINS);
        this.date = LocalDate.parse(date, DATE_FORMATTER);
    }

    /**
     * Constructs a {@code Date} with given {@code LocalDate}.
     *
     * @param date A valid {@code LocalDate}.
     */
    public Date(LocalDate date) {
        requireNonNull(date);
        this.date = date;
    }

    /**
     * Returns true if a given string is a valid {@code Date} with the format dd-MM-uuuu.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, DATE_FORMATTER);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Checks if this {@code Date} is after the specified date.
     *
     * @param other The other date to compare to.
     * @return True if this date is after the specified date.
     */
    public boolean isAfter(Date other) {
        return this.date.isAfter(other.date);
    }

    /**
     * Checks if this {@code Date} is before the specified date.
     *
     * @param other The other date to compare to.
     * @return True if this date is before the specified date.
     */
    public boolean isBefore(Date other) {
        return this.date.isBefore(other.date);
    }

    /**
     * Checks if this {@code Date} is within the date range.
     *
     * @param startDate The start date of the date range.
     * @param endDate The end date of the date range.
     * @return True if the {@code date} is within the date range.
     */
    public boolean isWithinDateRange(Date startDate, Date endDate) {
        if (isAfter(endDate)) {
            return false;
        }

        if (isBefore(startDate)) {
            return false;
        }

        return true;
    }

    /**
     * Compares this {@code Date} to another {@code Date}.
     *
     * @param other The other {@code Date}.
     * @return Negative integer if this {@code Date} is earlier and positive integer
     *     if this {@code Date} is later than the other {@code Date}, otherwise 0.
     */
    public int compareTo(Date other) {
        return date.compareTo(other.date);
    }

    @Override
    public String toString() {
        return this.date.format(DATE_DISPLAY_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
