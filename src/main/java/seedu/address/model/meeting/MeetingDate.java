package seedu.address.model.meeting;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.Optional;


/**
 * Represents a Meeting Date in the FinBook.
 * Guarantees: immutable; name is valid as declared in {@link #isValidMeetingDate(String)}
 */
public class MeetingDate {

    public static final String MESSAGE_CONSTRAINTS = "Date should be in the form of dd MMM yyyy [HH:mm]. "
        + "Meeting time is optional.";

    public static final String PLACEHOLDER_VALUE = "TBC";

    public final Optional<String> value;

    /**
     * Constructs a {@code Date}. Meeting date can be null
     *
     * @param date A valid date.
     */
    public MeetingDate(String date) {
        if (date != null && !date.isEmpty() && !date.equals(PLACEHOLDER_VALUE)) {
            checkArgument(isValidMeetingDate(date), MESSAGE_CONSTRAINTS);
            this.value = Optional.of(date);
        } else {
            this.value = Optional.empty();
        }

    }

    /**
     * Returns true if a given string is a valid date.
     * Checks if date is valid by parsing string to LocalDate
     */
    public static boolean isValidMeetingDate(String test) {
        if (test != null && !test.isEmpty() && !test.equals(PLACEHOLDER_VALUE)) {
            DateTimeFormatter formatter;
            try {
                if (test.contains(":")) {
                    formatter = DateTimeFormatter.ofPattern("d MMM uuuu HH:mm", Locale.ENGLISH)
                        .withResolverStyle(ResolverStyle.STRICT);
                    LocalDateTime.parse(test, formatter);
                } else {
                    formatter = DateTimeFormatter.ofPattern("d MMM uuuu", Locale.ENGLISH)
                        .withResolverStyle(ResolverStyle.STRICT);
                    ;
                    LocalDate.parse(test, formatter);
                }
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the value of the meeting date.
     */
    public String get() {
        return value.orElse(PLACEHOLDER_VALUE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof MeetingDate // instanceof handles nulls
            && (value != null && value.equals(((MeetingDate) other).value))); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return value.orElse(PLACEHOLDER_VALUE);
    }

}
