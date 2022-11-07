package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_FLAG_NOT_SPECIFIED = "No option flag specified.\n%s";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n%1$s";
    public static final String MESSAGE_INVALID_PROFILE_DISPLAYED_INDEX = "The profile index provided is invalid";
    public static final String MESSAGE_MULTIPLE_INVALID_PROFILE_DISPLAYED_INDEX = "One or more profile indexes "
            + "provided is invalid";
    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX = "The event index provided is invalid";
    public static final String MESSAGE_PROFILES_LISTED_OVERVIEW = "%1$d profiles listed!";
    public static final String MESSAGE_EVENTS_LISTED_OVERVIEW = "%1$d events listed!";
    public static final String MESSAGE_EVENTS_INVALID_START_END = "Start date should be before end date!";
    public static final String MESSAGE_EVENTS_HAS_TIME =
            "Start date and end dates must both be of either date only or date and time!";
}
