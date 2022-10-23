package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;

/**
 * Represents an Internship's interview date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidInterviewDate(String)}
 */
public class InterviewDate {

    public static final String MESSAGE_CONSTRAINTS = "Date should be in the format [d MMM yyyy] or [d/M/yyyy].\n"
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
    public static final DateTimeFormatter DATE_FORMAT = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("[d MMM yyyy]")
            .appendPattern("[d MMM]")
            .appendPattern("[d/M/yyyy]")
            .appendPattern("[d/M]")
            .parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDate.now().getYear())
            .toFormatter();

    public final String value;

    private final LocalDate interviewDate;

    /**
     *
     * @param interviewDate
     */
    public InterviewDate(String interviewDate) {
        requireNonNull(interviewDate);
        checkArgument(isValidInterviewDate(interviewDate), MESSAGE_CONSTRAINTS);
        this.interviewDate = LocalDate.parse(interviewDate, DATE_FORMAT);
        value = this.interviewDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    /**
     * Check if a given string is a valid interviewDate.
     * @return true if it is valid.
     */
    public static boolean isValidInterviewDate(String interviewDate) {
        try {
            DATE_FORMAT.parse(interviewDate);
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
                || (other instanceof InterviewDate // instanceof handles nulls
                && value.equals(((InterviewDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
