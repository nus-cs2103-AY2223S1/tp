package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tutorial's timeslot in the ModQuik.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimeslot(String)}
 */
public class TutorialTimeslot {

    public static final String MESSAGE_CONSTRAINTS =
            "Timeslot should be in HHMM - HHMM format, e.g 1600-1800";

    /*
     * Timeslot must be in HHMM-HHMM format.
     */
    public static final String VALIDATION_REGEX = "([01]?[0-9]|2[0-3])[0-5][0-9]-([01]?[0-9]|2[0-3])[0-5][0-9]";

    public final String timeslot;

    /**
     * Constructs a {@code TutorialVenue}.
     *
     * @param timeslot A valid timeslot.
     */
    public TutorialTimeslot(String timeslot) {
        requireNonNull(timeslot);
        checkArgument(isValidTimeslot(timeslot), MESSAGE_CONSTRAINTS);
        this.timeslot = timeslot;
    }

    /**
     * Returns true if a given string is a valid timeslot.
     */
    public static boolean isValidTimeslot(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return timeslot.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialTimeslot // instanceof handles nulls
                && timeslot.equals(((TutorialTimeslot ) other).timeslot)); // state check
    }

    @Override
    public int hashCode() {
        return timeslot.hashCode();
    }

}
