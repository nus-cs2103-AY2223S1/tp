package longtimenosee.model.event;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;


/**
 * Represents the date of an Event in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Date {

    public static final String STANDARD_DATE = "yyyy-MM-dd";

    public static final String MESSAGE_FORMAT_CONSTRAINTS = "Dates must follow Format: " + STANDARD_DATE;
    public static final String RANGE_FORMAT_CONSTRAINTS = "Ensure that Year/Month/Date values are valid dates.";

    public static final String YEAR_RANGE_CONSTRAINTS = "Ensure that Year entered is reasonable (i.e: Within the "
            + "20th century to 21st Century)";


    private static final String DATE_VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public final String value;
    private LocalDate date;

    /**
     * Main constructor for Date
     * @param value A valid Date parsed in YYYY-MM-DD
     */
    public Date(String value) {
        requireNonNull(value);
        checkArgument(isValidFormat(value), MESSAGE_FORMAT_CONSTRAINTS);
        this.value = value;
        this.date = parseDate(value);
    }

    /**
     * Requires a dateTime object to already have an appropriate format (YYYY-MM-DD).
     * Checks if given Year, Month, Date values are integer values in the appropriate range
     * (e.g: 0 <= MM <= 12)
     * @param date in YYYY-MM-DD.
     */
    public static boolean isValidRange(String date) {
        assert (isValidFormat(date) == true);
        try {
            LocalDate dateTime = LocalDate.parse(date);
        } catch (DateTimeParseException dte) {
            return false;
        }
        return true;
    }
    /**
     * Requires a dateTime object to already have an appropriate format (YYYY-MM-DD).
     * Checks if given Year for an event is reasonable (i.e: within the 20th to 21st century)
     * @param date in YYYY-MM-DD.
     */
    public static boolean isValidYear(String date) {
        assert (isValidFormat(date) == true);
        try {
            LocalDate dateTime = LocalDate.parse(date);
            int year = dateTime.getYear();
            if (year <= 1900 || year >= 2100) {
                return false;
            }
        } catch (DateTimeParseException dte) {
            return false;
        }
        return true;
    }

    private LocalDate parseDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STANDARD_DATE);
        try {
            LocalDate dateTime = LocalDate.parse(value);
        } catch (DateTimeParseException dateTimeException) {
            throw dateTimeException;
        }
        LocalDate dateTime = LocalDate.parse(value, formatter);
        return dateTime;
    }

    public static boolean isValidFormat(String value) {
        return value.matches(DATE_VALIDATION_REGEX);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Date)) {
            return false;
        }
        Date otherDate = (Date) other;
        return otherDate.date.equals(this.date)
                && otherDate.value.equals(this.value);
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG));
    }

    public LocalDate getDate() {
        return this.date;
    }

}
