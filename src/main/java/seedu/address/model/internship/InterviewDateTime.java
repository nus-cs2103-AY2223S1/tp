package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;

/**
 * Represents an Internship's interview date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidInterviewDateTime(String)}
 */
public class InterviewDateTime {

    public static final String MESSAGE_CONSTRAINTS = "Date should be in the format [d MMM HH:mm] or [d/M/yyyy HH:mm].\n"
            + "Year can be omitted to default to current year.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$";

    /*
     * For the dateTime 23/10/2022 09:00, the following formats are accepted:
     * 23 Oct 2022 09:00, 23 Oct 09:00, 23/10/2022 09:00, 23/10 09:00
     */
    public static final DateTimeFormatter DATE_FORMAT = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("[d/M/yyyy HH:mm]")
            .appendPattern("[d MMM yyyy HH:mm]")
            .appendPattern("[d MMM yyyy, h:mm a]")
            .appendPattern("[d MMM HH:mm]")
            .appendPattern("[d/M/yyyy HH:mm]")
            .appendPattern("[d/M HH:mm]")
            .parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDate.now().getYear())
            .toFormatter();

    public final String value;

    private final LocalDateTime interviewDateTime;

    /**
     *
     * @param interviewDateTime
     */
    public InterviewDateTime(String interviewDateTime) {
        requireNonNull(interviewDateTime);
        checkArgument(isValidInterviewDateTime(interviewDateTime), MESSAGE_CONSTRAINTS);
        this.interviewDateTime = LocalDateTime.parse(interviewDateTime, DATE_FORMAT);
        value = this.interviewDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,
                FormatStyle.SHORT));

    }

    /**
     * Check if a given string is a valid interviewDate.
     * @return true if it is valid.
     */
    public static boolean isValidInterviewDateTime(String interviewDateTime) {
        try {
            DATE_FORMAT.parse(interviewDateTime);
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
                || (other instanceof InterviewDateTime // instanceof handles nulls
                && value.equals(((InterviewDateTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}


