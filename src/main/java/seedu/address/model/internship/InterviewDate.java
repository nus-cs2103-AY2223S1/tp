package seedu.address.model.internship;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Internship's interview date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDatetimeStr(String)} (String)}
 */
public class InterviewDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Interview dates must be in the format yyyy-MM-dd HH:mm, and it should not blank";

    public static final String VALIDATION_REGEX = "\\d{4}-[01]\\d-[0-3]\\d\\s[0-2]\\d((:[0-5]\\d)?){2}";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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

    public static boolean isValidDatetimeStr(String test) {
        return test.matches(VALIDATION_REGEX);
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
        if (other == this) {
            // short circuit if same object
            return true;
        } else if (other instanceof InterviewDate) {
            // instanceof handles nulls
            if (datetime == null || ((InterviewDate) other).datetime == null) {
                // at least 1 of the values are null
                return datetime == ((InterviewDate) other).datetime;
            }
            return datetime.equals(((InterviewDate) other).datetime); // state check
        }
        return false; // this is not null while other is null
    }

    @Override
    public int hashCode() {
        return datetime.hashCode();
    }
}
