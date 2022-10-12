package seedu.nutrigoals.model.user;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.AppUtil.checkArgument;

/**
 * Represents a Height object
 */
public class Height {
    public static final int MAX_HEIGHT = 220;
    public static final int MIN_HEIGHT = 0;
    public static final String VALIDATION_REGEX = "\\d+";
    public static final String MESSAGE_CONSTRAINTS = "Height must take on a positive number that is not too large.";
    public final String value;

    /**
     * Initialises a Height object and checks if the specified input is a valid height
     * @param height
     */
    public Height(String height) {
        requireNonNull(height);
        checkArgument(isValidHeight(height), MESSAGE_CONSTRAINTS);
        value = height;
    }

    /**
     * Checks if input height is valid
     * @param height
     * @return height is a number and height is < 220
     */
    public static boolean isValidHeight(String height) {
        if (!height.isEmpty() && height.matches(VALIDATION_REGEX)) {
            try {
                Integer heightValue = Integer.parseInt(height);
                return heightValue >= MIN_HEIGHT && heightValue < MAX_HEIGHT;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Height) {
            Height other = (Height) obj;
            return this.value.equals(other.value);
        }
        return false;
    }
}
