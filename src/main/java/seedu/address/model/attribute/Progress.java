package seedu.address.model.attribute;

/**
 * A class that represents Progress as an attribute.
 */
public class Progress extends AbstractAttribute<String> {
    public static final String TYPE = "Progress";
    public static final String MESSAGE_CONSTRAINTS = "Progress should be set to 25%, 50%, 75% or 100%.";

    /**
     * Constructor to initiate progress.
     * @param level
     */
    public Progress(String level) {
        super(TYPE, String.valueOf(level));
    }

    /**
     * Method to check the input for setting progress.
     * @param test
     * @return true if input is valid.
     */
    public static boolean isValidProgress(String test) {
        if (test == "25%" || test == "50%" || test == "75%" || test == "100%") {
            return true;
        }
        return false;
    }
}
