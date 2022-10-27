package seedu.address.model.attribute;

public class Progress extends AbstractAttribute<String>{
    public static final String TYPE = "Progress";
    public static final String MESSAGE_CONSTRAINTS = "Progress should be set to 25%, 50%, 75% or 100%.";

    public Progress(String level) {
        super(TYPE, String.valueOf(level));
    }

    public static boolean isValidProgress(String test) {
        if (test == "25%" || test == "50%" || test == "75%" || test == "100%") {
            return true;
        }
        return false;
    }
}
