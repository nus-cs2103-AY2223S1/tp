package gim.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX = "The exercise index provided is invalid";
    public static final String MESSAGE_EXERCISES_LISTED_OVERVIEW = "%1$d exercises listed!";

    /**
     * Specific messages for the {@RangeCommand} feature (variation two).
     * Note: variation two follows the format :range last/INTEGER
     */
    public static final String MESSAGE_RANGE_COMMAND_TWO = "Exercises from the last %1$d days listed!";
    public static final String MESSAGE_RANGE_COMMAND_TWO_TODAY = "Exercises from today listed!";
    public static final String MESSAGE_RANGE_COMMAND_TWO_YESTERDAY = "Exercises since yesterday listed!";
    public static final String MESSAGE_RANGE_COMMAND_TWO_WEEK =
            "Exercises since the last week (i.e. last 7 days) listed!";

}
