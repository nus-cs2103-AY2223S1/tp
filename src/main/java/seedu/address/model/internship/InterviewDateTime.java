package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Comparator;

/**
 * Represents an Internship's interview date and time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidInterviewDateTime(String)}
 */
public class InterviewDateTime {

    public static final String MESSAGE_CONSTRAINTS = "Date/time should be one of these formats:\n"
            + "[d MMM yyyy HH:mm] or [d/M/yyyy HH:mm] or [d MMM yyyy, h:mm a] or [d/M/yyyy, h:mm a]\n"
            + "Year can be omitted to default to current year.\n"
            + "When using AM/PM format, include a comma after the date.";

    /*
     * For the dateTime 23/10/2022 09:00, the following formats are accepted:
     * "23/10/2022 09:00", "23 Oct 2022 09:00", "23 Oct 09:00", "23 Oct 2022, 9:00 AM", "23/10 09:00"
     */
    public static final DateTimeFormatter INPUT_DATE_FORMAT = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("[d MMM yyyy HH:mm]")
            .appendPattern("[d MMM yyyy, h:mm a]")
            .appendPattern("[d MMM HH:mm]")
            .appendPattern("[d MMM, h:mm a]")
            .appendPattern("[d/M/yyyy HH:mm]")
            .appendPattern("[d/M/yyyy, h:mm a]")
            .appendPattern("[d/M HH:mm]")
            .appendPattern("[d/M, h:mm a]")
            .parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDate.now().getYear())
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
        checkArgument(isValidInterviewDateTime(interviewDateTime), MESSAGE_CONSTRAINTS);
        this.interviewDateTime = LocalDateTime.parse(interviewDateTime, INPUT_DATE_FORMAT);
        value = this.interviewDateTime.format(DISPLAY_DATE_FORMAT);
    }

    /**
     * Check if a given string is a valid interviewDateTime.
     * @return true if it is valid.
     */
    public static boolean isValidInterviewDateTime(String interviewDateTime) {
        if (interviewDateTime.isEmpty()) {
            return false;
        }

        try {
            INPUT_DATE_FORMAT.parse(interviewDateTime);
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
