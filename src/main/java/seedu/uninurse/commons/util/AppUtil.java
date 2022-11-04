package seedu.uninurse.commons.util;

import static java.util.Objects.requireNonNull;

import javafx.scene.image.Image;
import seedu.uninurse.MainApp;

/**
 * A container for App specific utility functions
 */
public class AppUtil {
    /**
     * Gets an Image from the specified path.
     */
    public static Image getImage(String imagePath) {
        requireNonNull(imagePath);
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    /**
     * Checks that condition is true. Used for validating arguments to methods.
     *
     * @throws IllegalArgumentException if condition is false.
     */
    public static void checkArgument(Boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks that condition is true. Used for validating arguments to methods.
     *
     * @throws IllegalArgumentException with errorMessage if condition is false.
     */
    public static void checkArgument(Boolean condition, String errorMessage) {
        if (!condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
