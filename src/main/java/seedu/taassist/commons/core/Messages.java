package seedu.taassist.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format: \n%1$s.";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid.";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";
    public static final String MESSAGE_MODULE_CLASS_DOES_NOT_EXIST = "A provided class does not exist.\n"
            + "Existing classes: %1$s";
    public static final String MESSAGE_NOT_IN_FOCUS_MODE = "Usage of %s requires you to be in focus mode to be used!";
    public static final String MESSAGE_INVALID_SESSION = "The session %1$s does not exist in class %2$s.";
}
