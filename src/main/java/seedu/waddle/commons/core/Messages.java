package seedu.waddle.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_COPY_ERROR = "Unable to copy to clipboard.";
    public static final String MESSAGE_OVER_BUDGET = "The provided cost exceeds the budget."
            + " Please increase the budget or lower the cost.";
    public static final String MESSAGE_ITINERARY_OVER_BUDGET = "The provided budget is insufficient to cover the cost."
            + " Please increase the budget or lower the cost.";
    public static final String MESSAGE_UNAVAILABLE_COMMAND_HOME = "Command is unavailable in the home page.";
    public static final String MESSAGE_UNAVAILABLE_COMMAND_ITINERARY = "Command is unavailable in the itinerary page.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX = "The itinerary index provided is invalid.";
    public static final String MESSAGE_INVALID_ITEM_DISPLAYED_INDEX = "The item index provided is invalid.";
    public static final String MESSAGE_ITINERARIES_LISTED_OVERVIEW = "%1$d itineraries listed!";
    public static final String MESSAGE_INVALID_STAGE = "The stage you provided is invalid! \n%1$s";
    public static final String MESSAGE_CONFLICTING_ITEMS = "Quack, there is a time clash!"
            + "\nThe provided time clashes with:\n%1$sPlease change the start time and/or the duration.";
    public static final String MESSAGE_ITEM_PAST_MIDNIGHT =
            "%1$s extends past midnight which is not currently supported.\n"
                    + "Please split %1$s into 2 parts and plan the second part at the start of the next day.";
    // not meant for users to see
    public static final String MESSAGE_UNKNOWN_STAGE = "Unknown stage, something went wrong with the StateManager.";
}

