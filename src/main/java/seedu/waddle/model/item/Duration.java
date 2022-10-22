package seedu.waddle.model.item;

import static seedu.waddle.commons.util.AppUtil.checkArgument;

/**
 * Represents an Item's duration in minutes.
 */
public class Duration {
    public static final String MESSAGE_CONSTRAINTS = "Duration should only contain a positive number.";
    private final int duration;
    private final boolean isNull;

    /**
     * Constructs a {@code Duration}.
     *
     * @param duration A valid duration.
     */
    public Duration(String duration) {
        if (duration == null) {
            this.isNull = true;
            this.duration = 0;
        } else {
            checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
            this.duration = Integer.valueOf(duration);
            this.isNull = false;
        }
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

    public boolean isNull() {
        return isNull;
    }
    @Override
    public String toString() {
        return String.valueOf(duration);
    }
}
