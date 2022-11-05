package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INVALID_UNDO = "Nothing to be undone!";
    public static final String MESSAGE_INVALID_REDO = "Nothing to be redone!";
    public static final String MESSAGE_INVALID_LESSON_TIME = "The start time of the lesson, %s,  cannot be earlier "
            + "than or the same as the end time of the lesson, %s.";
}
