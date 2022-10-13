package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
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
        requireNonNull(datetimeStr);
        checkArgument(isValidDatetimeStr(datetimeStr), MESSAGE_CONSTRAINTS);

        LocalDateTime datetime = LocalDateTime.parse(datetimeStr, formatter);
        this.datetime = datetime;
    }

    public static boolean isValidDatetimeStr(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return datetime.format(formatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewDate // instanceof handles nulls
                && datetime.equals(((InterviewDate) other).datetime)); // state check
    }

    @Override
    public int hashCode() {
        return datetime.hashCode();
    }
}
