package jeryl.fyp.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "There is no Student with the input StudentId!";
    public static final String MESSAGE_PROJECTS_LISTED_OVERVIEW = "%1$d projects listed!";
    public static final String MESSAGE_INVALID_DEADLINE_RANK = "The Rank provided should be a valid integer";
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DATETIME = "Invalid datetime format given!\n"
            + "Consider using \"dd-MM-yyyy HH:mm\"\n"
            + "Example: 20-10-2022 13:57";
}
