package seedu.application.model.application.interview;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.application.model.CommonRegex;

/**
 * Represents the Time of Interview for the specific Application.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class InterviewTime {


    public static final String MESSAGE_CONSTRAINTS = "Time should be in the format of HHMM, ranging from 0000 to 2359.";
    public static final DateTimeFormatter COMMAND_TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    public static final DateTimeFormatter DISPLAY_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;
    private static final String INVALID_MIDNIGHT = "^2400$";
    public final LocalTime value;

    /**
     * Constructs a {@code InterviewTime}.
     *
     * @param timeString A valid time in String.
     */
    public InterviewTime(String timeString) {
        requireNonNull(timeString);
        checkArgument(isValidTime(timeString), MESSAGE_CONSTRAINTS);
        value = this.parseLocalTime(timeString);
    }

    /**
     * Returns true if a given string is a valid time string.
     */
    public static boolean isValidTime(String test) {
        try {
            COMMAND_TIME_FORMATTER.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return !test.matches(INVALID_MIDNIGHT);
    }

    /**
     * Returns true if a given string contains another prefix or prefix, argument pair.
     */
    public static boolean hasAPrefix(String test) {
        String[] filteredTest = test.split(" ");
        if (filteredTest.length == 2) {
            return filteredTest[1].matches(CommonRegex.VALIDATION_REGEX_FOR_EXTRA_PREFIX_DATE_TIME);
        }
        return false; //error not cause by extra prefix
    }

    private LocalTime parseLocalTime(String timeString) {
        return LocalTime.parse(timeString, COMMAND_TIME_FORMATTER);
    }

    /**
     * Returns the command string that corresponds to this {@code InterviewTime}.
     *
     * @return The command string that corresponds to this {@code InterviewTime}.
     */
    public String toCommandString() {
        return COMMAND_TIME_FORMATTER.format(value);
    }

    @Override
    public String toString() {
        return this.value.format(DISPLAY_TIME_FORMATTER).substring(0, 5);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewTime // instanceof handles nulls
                && value.equals(((InterviewTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
