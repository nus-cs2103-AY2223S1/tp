package seedu.masslinkers.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Invalid command! Enter help to view all valid commands.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INCOMPLETE_COMMAND_FORMAT = "Incomplete command format! \n%1$s";
    public static final String MESSAGE_MISSING_ARGUMENTS = "Missing arguments for the given command! \n%1$s";
    public static final String MESSAGE_INVALID_ARGUMENTS = "Invalid arguments '%1$s' provided to the command!";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid.";
    public static final String MESSAGE_PHONE_WARNING = "This student's phone number appears to be invalid to "
            + "Mass Linkers, but we're allowing it. You may change it via the edit command.\n";
    public static final String MESSAGE_INVALID_INTERESTS = "Interests should be alphanumeric.";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";
    public static final String MESSAGE_ONE_STUDENT_LISTED_OVERVIEW = "%1$d student listed!";

}
