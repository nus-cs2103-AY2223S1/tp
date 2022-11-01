package seedu.waddle.model.item;

import static seedu.waddle.commons.util.AppUtil.checkArgument;

/**
 * Represents an Item's duration in minutes.
 */
public class Duration {
    public static final String MESSAGE_CONSTRAINTS = "Duration must be more than 0 minutes and shorter than"
            + "1440 minutes (1 day).";
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

    public int getValue() {
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
        return value > 0 && value <= 1440;
    }
    @Override
    public String toString() {
        return String.valueOf(duration);
    }
}
