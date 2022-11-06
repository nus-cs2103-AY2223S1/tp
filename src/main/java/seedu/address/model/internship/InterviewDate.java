package seedu.address.model.internship;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;

/**
 * Represents an Internship's interview date in InterNUS.
 * Guarantees: immutable; is valid as declared in {@link #isValidDatetimeStr(String)} (String)}
 */
public class InterviewDate {
    public static final String MESSAGE_CONSTRAINTS = "Interview date must be "
            + "a valid date (present in a calendar) with valid time (24-hour format), "
            + "and given in the exact format yyyy-MM-dd HH:mm.";

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm").withResolverStyle(ResolverStyle.STRICT);

    public final LocalDateTime datetime;

    /**
     * Constructs a {@code InterviewDate}.
     *
     * @param datetimeStr A valid String in the format yyyy-MM-dd HH:mm that represents a date and time.
     */
    public InterviewDate(String datetimeStr) {
        if (datetimeStr == null || datetimeStr.isBlank()) {
            this.datetime = null;
        } else {
            checkArgument(isValidDatetimeStr(datetimeStr), MESSAGE_CONSTRAINTS);

            LocalDateTime datetime = LocalDateTime.parse(datetimeStr, formatter);
            this.datetime = datetime;
        }
    }

    /**
     * Returns true if the given String can be parsed to a valid {@Code LocalDateTime}.
     */
    public static boolean isValidDatetimeStr(String test) {
        try {
            LocalDateTime.parse(test, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        if (datetime == null) {
            return "No interviews scheduled";
        }
        return datetime.format(formatter);
    }

    @Override
    public boolean equals(Object other) {
        return Objects.equals(datetime, ((InterviewDate) other).datetime);
    }

    @Override
    public int hashCode() {
        return datetime.hashCode();
    }
}
