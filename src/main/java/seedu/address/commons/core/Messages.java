package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    //---------------------PERSON SPECIFIC MESSAGES-----------------------
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d person(s) listed!";

    //---------------------EVENT SPECIFIC MESSAGES------------------------
    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX = "The event index provided is invalid";
    public static final String MESSAGE_EVENTS_LISTED_OVERVIEW = "%1$d event(s) listed!";
    public static final String MESSAGE_INVALID_INDEX_VALUE = "%s is not a valid index,"
            + " index should be a non-zero unsigned integer.";
    public static final String MESSAGE_DUPLICATE_INDEXES = "%s is duplicated, the input indexes must be distinct.";

}
