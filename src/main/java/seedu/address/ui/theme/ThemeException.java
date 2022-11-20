package seedu.address.ui.theme;

/**
 * Throws an error when a css theme does not exist.
 */
public class ThemeException extends Exception {
    public ThemeException(String message) {
        super(message);
    }
}
