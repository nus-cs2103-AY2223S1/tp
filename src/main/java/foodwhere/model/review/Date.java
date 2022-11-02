package foodwhere.model.review;

import static foodwhere.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;

/**
 * Represents a Review's date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date implements Comparable<Date> {

    public static final String MESSAGE_CONSTRAINTS = "Dates can be written as DD/MM/YYYY or DD-MM-YYYY";
    public static final String VALID_DATE_CONSTRAINTS = "Dates have to be a valid date";

    public static final String VALIDATION_REGEX = "\\d{1,2}-\\d{1,2}-\\d{4}|\\d{1,2}/\\d{1,2}/\\d{4}";

    //@@author clarence-chew-reused
    //Reused from https://stackoverflow.com/a/30478777
    // with minor modifications
    public static final DateTimeFormatter SLASH_DATE_FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("d/M/yyyy")
            .parseDefaulting(ChronoField.ERA, 1)
            .toFormatter()
            .withChronology(IsoChronology.INSTANCE)
            .withResolverStyle(ResolverStyle.STRICT);
    public static final DateTimeFormatter DASH_DATE_FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("d-M-yyyy")
            .parseDefaulting(ChronoField.ERA, 1)
            .toFormatter()
            .withChronology(IsoChronology.INSTANCE)
            .withResolverStyle(ResolverStyle.STRICT);
    //@@author
    public static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter[] PARSING_DATE_FORMATS =
            new DateTimeFormatter[]{ SLASH_DATE_FORMAT, DASH_DATE_FORMAT };

    public final String value;

    public final LocalDate date;

    /**
     * Constructs an {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidFormat(date), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDate(date), VALID_DATE_CONSTRAINTS);
        this.date = parseDate(date, PARSING_DATE_FORMATS);
        value = this.date.format(OUTPUT_DATE_FORMAT);
    }

    /**
     * Parses a date with formats given until one works.
     *
     * @param date The string to be parsed.
     * @param formats The formats to use to parse the string.
     * @return Successfully parsed date.
     * @throws IllegalArgumentException If the string cannot be parsed.
     */
    private static LocalDate parseDate(String date, DateTimeFormatter[] formats) {
        for (DateTimeFormatter format : formats) {
            try {
                // does not return null
                LocalDate result = LocalDate.parse(date, format);
                return result;
            } catch (DateTimeParseException ex) {
                // fallthrough - try another parser
            }
        }
        throw new IllegalArgumentException(VALID_DATE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is in a valid format.
     */
    public static boolean isValidFormat(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        if (!isValidFormat(test)) {
            return false;
        }
        try {
            parseDate(test, PARSING_DATE_FORMATS);
        } catch (IllegalArgumentException ex) {
            // not a valid date
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
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Compares two dates.
     * @param other The object to be compared.
     * @return 1 if this date is later, -1 if this date is earlier, 0 if the dates are the same.
     */
    public int compareTo(Date other) {
        requireNonNull(other);
        return date.compareTo(other.date);
    }
}
