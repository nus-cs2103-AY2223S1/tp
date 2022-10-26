package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    /* Failure messages */
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_MISSING_PREFIXES_ALL =
            "This command is missing all of the following prefixes: %s\n%s";
    public static final String MESSAGE_MISSING_PREFIXES_SOME =
            "This command requires at least one of the following prefixes: %s\n%s";
    public static final String MESSAGE_EMPTY_PREFIXES =
            "This command's prefixes should not be empty: %s\n%s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX = "The tutorial index provided is invalid";
    public static final String MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX = "The reminder index provided is invalid";
    public static final String MESSAGE_INVALID_CONSULTATION_DISPLAYED_INDEX =
            "The consultation index provided is invalid";

    /* Success messages */
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";

}
