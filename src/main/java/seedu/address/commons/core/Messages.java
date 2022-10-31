package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid.";
    public static final String MESSAGE_INVALID_MODULE_DISPLAYED_INDEX = "The module index provided is invalid.";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d person(s) listed!";
    public static final String MESSAGE_MODULES_LISTED_OVERVIEW = "%1$d module(s) listed!";
    public static final String MESSAGE_MODULE_LISTED = "Here's the module you requested!";
    public static final String MESSAGE_NO_SUCH_PERSON = "No such person in current displayed list!\n";
    public static final String MESSAGE_NO_SUCH_PERSON_DELETE = "No such person in current displayed list!\n"
            + "Make sure the person that you want to delete is listed";
    public static final String MESSAGE_INVALID_TASK_NUMBER = "The task number must be a positive integer!";
    public static final String MESSAGE_INVALID_TASK_NUMBERS_TO_SWAP =
            "You must provide two different task numbers to swap!";
    public static final String MESSAGE_INVALID_NUMBER_OF_TASK_NUMBERS_TO_SWAP =
            "You must provide exactly two task numbers!";
    public static final String MESSAGE_NO_SUCH_TASK_NUMBER = "Task number given does not exist.";
    public static final String MESSAGE_NO_SUCH_MODULE = "No such module in list!";
    public static final String MESSAGE_NO_MODULE_IN_FILTERED_LIST =
            "Please create or navigate to `%s` to perform the operation!";
    public static final String MESSAGE_NOT_AT_HOMEPAGE =
            "Hey! You are not on the home page!";
}
