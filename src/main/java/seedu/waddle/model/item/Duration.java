package seedu.waddle.model.item;

import static seedu.waddle.commons.util.AppUtil.checkArgument;

/**
 * Represents an Item's duration in minutes.
 */
public class Duration {
    public static final String MESSAGE_CONSTRAINTS = "Duration should be more than or equals to 0.";
    private final int duration;

    /**
     * Constructs a {@code Duration}.
     *
     * @param duration A valid duration.
     */
    public Duration(String duration) {
        checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
        this.duration = Integer.valueOf(duration);
    }

    public int getDuration() {
        return this.duration;
    }

    /**
     * Returns true if the given string is a positive integer or null.
     */
    public static boolean isValidDuration(String test) {
        if (test == null) {
            return true;
        }
        int value;
        try {
            value = Integer.valueOf(test);
        } catch (NumberFormatException e) {
            return false;
        }
        return value >= 0;
    }
    @Override
    public String toString() {
        return String.valueOf(duration);
    }
}
