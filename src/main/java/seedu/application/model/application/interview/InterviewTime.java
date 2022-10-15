package seedu.application.model.application.interview;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;

/**
 * Represents the Time of Interview for the specific Application.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class InterviewTime {


    public static final String MESSAGE_CONSTRAINTS = "Time should be in the format of HHMM";
    //Only valid if time in HHMM format
    public static final String VALIDATION_REGEX = "^([0-1]?[0-9]|2[0-3])[0-5][0-9]$";
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
        return test.matches(VALIDATION_REGEX);
    }

    private LocalTime parseLocalTime(String timeString) {
        //desired date format HHMM e.g."2359"
        String validatedTimeString = timeString.substring(0, 2) + ":" + timeString.substring(2, 4) + ":" + "00";
        return LocalTime.parse(validatedTimeString);
    }

    @Override
    public String toString() {
        return this.value.toString();
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