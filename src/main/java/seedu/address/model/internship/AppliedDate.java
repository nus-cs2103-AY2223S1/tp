package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Represents an Internship's applied date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppliedDate(String)}
 */
public class AppliedDate {

    public static final String MESSAGE_CONSTRAINTS = "Date should be one of these formats:\n"
            + "[d MMM yyyy] or [d/M/yyyy]\n"
            + "Year can be omitted to default to current year.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$";

    /*
     * For the date 23/10/2022, the following formats are accepted:
     * 23 Oct 2022, 23 Oct, 23/10/2022, 23/10
     */
    public static final DateTimeFormatter INPUT_DATE_FORMAT = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("[d MMM yyyy]")
            .appendPattern("[d MMM]")
            .appendPattern("[d/M/yyyy]")
            .appendPattern("[d/M]")
            .parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDate.now().getYear())
            .toFormatter();

    public static final DateTimeFormatter DISPLAY_DATE_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");

    public final String value;

    private final LocalDate appliedDate;

    /**
     * Constructs an {@code AppliedDate}.
     *
     * @param appliedDate A valid appliedDate.
     */
    public AppliedDate(String appliedDate) {
        requireNonNull(appliedDate);
        checkArgument(isValidAppliedDate(appliedDate), MESSAGE_CONSTRAINTS);
        this.appliedDate = LocalDate.parse(appliedDate, INPUT_DATE_FORMAT);
        value = this.appliedDate.format(DISPLAY_DATE_FORMAT);
    }

    /**
     * Returns true if a given string is a valid appliedDate.
     */
    public static boolean isValidAppliedDate(String appliedDate) {
        try {
            INPUT_DATE_FORMAT.parse(appliedDate);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppliedDate // instanceof handles nulls
                && value.equals(((AppliedDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
