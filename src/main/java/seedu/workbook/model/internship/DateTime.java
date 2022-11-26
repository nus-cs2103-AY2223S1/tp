package seedu.workbook.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a DateTime in WorkBook.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {
    public static final DateTime EMPTY_DATETIME = new DateTime("");
    public static final String MESSAGE_CONSTRAINTS = "Date should be formatted as dd-mmm-yyyy hh:mm,"
            + " and should actually exist.";

    private static final String datePattern = "d-MMM-uuuu HH:mm";
    private static final DateTimeFormatterBuilder formatterBuilder = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(datePattern);
    private static final DateTimeFormatter dateFormatter = formatterBuilder.toFormatter()
        .withResolverStyle(ResolverStyle.STRICT);


    public final String value;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid dateTime.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        if (!dateTime.isEmpty()) {
            checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
            String str = String.join(" ", dateTime.split("\\s+", 2));
            this.value = LocalDateTime.parse(str, dateFormatter).format(dateFormatter);
        } else {
            this.value = "";
        }
    }

    /**
     * Returns true if a given string is a valid {@code DateTime}.
     */
    public static boolean isValidDateTime(String test) {
        try {
            if (test.isEmpty()) {
                return true;
            }
            String str = String.join(" ", test.split("\\s+", 2));
            LocalDateTime.parse(str, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Note that DateTimeParseException will be thrown if this is called on DateTime.EMPTY
     */
    public boolean isAfter(DateTime other) {
        LocalDateTime thisDate = LocalDateTime.parse(this.value, dateFormatter);
        LocalDateTime otherDate = LocalDateTime.parse(other.value, dateFormatter);
        return thisDate.isAfter(otherDate);
    }

    /**
     * Note that DateTimeParseException will be thrown if this is called on DateTime.EMPTY
     */
    public boolean isPast() {
        LocalDateTime thisDate = LocalDateTime.parse(this.value, dateFormatter);
        return LocalDateTime.now().isAfter(thisDate);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && value.equals(((DateTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
