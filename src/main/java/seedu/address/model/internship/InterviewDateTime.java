package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.Comparator;

/**
 * Represents an Internship's interview date and time in findMyIntern.
 * Guarantees: immutable; is valid as declared in {@link #isValidInterviewDateTime(String)}
 */
public class InterviewDateTime {

    public static final String FORMAT_CONSTRAINTS = "Date/time should be one of these formats:\n"
            + "[d MMM yyyy HH:mm] or [d/M/yyyy HH:mm] or [d MMM yyyy, h:mm a] or [d/M/yyyy, h:mm a]\n"
            + "Year can be omitted to default to current year.\n"
            + "When using AM/PM format, include a comma after the date.";

    public static final String DATE_TIME_CONSTRAINTS = "Date and time provided is invalid.";

    /*
     * For the dateTime 23/10/2022 09:00, the following formats are accepted:
     * "23/10/2022 09:00", "23 Oct 2022 09:00", "23 Oct 09:00", "23 Oct 2022, 9:00 AM", "23/10 09:00"
     */
    public static final DateTimeFormatter INPUT_DATE_FORMAT = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("[d MMM uuuu HH:mm]")
            .appendPattern("[d MMM uuuu, h:mm a]")
            .appendPattern("[d MMM HH:mm]")
            .appendPattern("[d MMM, h:mm a]")
            .appendPattern("[d/M/uuuu HH:mm]")
            .appendPattern("[d/M/uuuu, h:mm a]")
            .appendPattern("[d/M HH:mm]")
            .appendPattern("[d/M, h:mm a]")
            .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
            .toFormatter();

    public static final DateTimeFormatter DISPLAY_DATE_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

    public final String value;

    private final LocalDateTime interviewDateTime;

    /**
     * Constructs an {@code InterviewDateTime}.
     * @param interviewDateTime A valid interviewDateTime.
     */
    public InterviewDateTime(String interviewDateTime) {
        requireNonNull(interviewDateTime);
        checkArgument(isValidFormat(interviewDateTime), FORMAT_CONSTRAINTS);
        checkArgument(isValidDateTime(interviewDateTime), DATE_TIME_CONSTRAINTS);
        this.interviewDateTime = LocalDateTime.parse(interviewDateTime, INPUT_DATE_FORMAT);
        value = this.interviewDateTime.format(DISPLAY_DATE_FORMAT);
    }

    /**
     * Returns true if a given string has a valid DateTime format.
     * @param interviewDateTime The given string to be checked.
     * @return true if it is valid.
     */
    public static boolean isValidFormat(String interviewDateTime) {
        if (interviewDateTime.isEmpty()) {
            return false;
        }

        try {
            INPUT_DATE_FORMAT.withResolverStyle(ResolverStyle.LENIENT).parse(interviewDateTime);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string represents a valid date and time.
     * @param interviewDateTime The given string to be checked.
     * @return true if it is valid.
     */
    public static boolean isValidDateTime(String interviewDateTime) {
        if (interviewDateTime.isEmpty()) {
            return false;
        }

        try {
            INPUT_DATE_FORMAT.withResolverStyle(ResolverStyle.STRICT).parse(interviewDateTime);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Check if a given string is a valid interviewDateTime.
     * @param interviewDateTime The given string to be checked.
     * @return true if it is valid.
     */
    public static boolean isValidInterviewDateTime(String interviewDateTime) {
        return isValidFormat(interviewDateTime) && isValidDateTime(interviewDateTime);
    }

    /**
     * Checks if two interviewDateTime objects are equal, even if both are null, and returns the result.
     * @param interviewDateTime1 First InterviewDateTime.
     * @param interviewDateTime2 Second InterviewDateTime.
     * @return true if both are equal.
     */
    public static boolean bothNullOrEqual(InterviewDateTime interviewDateTime1, InterviewDateTime interviewDateTime2) {
        if (interviewDateTime1 == null) {
            return null == interviewDateTime2;
        } else {
            return interviewDateTime1.equals(interviewDateTime2);
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

    public static Comparator<Internship> getComparator() {
        return (i1, i2) -> {
            InterviewDateTime t1 = i1.getInterviewDateTime();
            InterviewDateTime t2 = i2.getInterviewDateTime();
            if (t1 == null && t2 == null) {
                return 0;
            } else if (t1 == null) {
                return 1;
            } else if (t2 == null) {
                return -1;
            }
            return -t1.interviewDateTime.compareTo(t2.interviewDateTime);
        };
    }

}
