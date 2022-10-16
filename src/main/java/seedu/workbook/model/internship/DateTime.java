package seedu.workbook.model.internship;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Date in WorkBook.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDate(String)}
 */
public class DateTime {

    private static final String datePattern = "d-MMM-yyyy HH:mm";
    private static final DateTimeFormatterBuilder formatterBuilder = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern(datePattern);
    private static final DateTimeFormatter dateFormatter = formatterBuilder.toFormatter();

    public static final String MESSAGE_CONSTRAINTS = "Date should be dd-mmm-yyyy hh:mm";

    public final String value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public DateTime(String date) {
        requireNonNull(date);
        if (!date.isEmpty()) {
            checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
            String str = String.join(" ", date.split("\\s+", 2));
            this.value = LocalDateTime.parse(str, dateFormatter).format(dateFormatter);
        } else {
            this.value = "";
        }
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
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
