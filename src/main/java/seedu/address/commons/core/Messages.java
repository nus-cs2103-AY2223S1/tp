package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_COMMAND_WITH_HELP_FORMAT = "Invalid command format! \nTry '%1$s "
            + "--help' for more information.";
    public static final String MESSAGE_INVALID_COMMAND_WITH_HELP_COMMAND = "Invalid command format! \nTry 'help'"
            + " for more information.";
    public static final String MESSAGE_MISSING_ARGUMENTS_FORMAT = "Invalid command format! \n%1$s\nTry '%2$s --help' "
            + "for "
            + "more information.";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";

    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_INVALID_LINK_DISPLAYED_INDEX = "The link index provided is invalid";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_PARSE_EXCEPTION =
            "Try adding --help for more information";

}
