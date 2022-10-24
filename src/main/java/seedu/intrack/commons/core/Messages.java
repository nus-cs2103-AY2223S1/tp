package seedu.intrack.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    public static final String MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX = "The internship index provided is invalid.";

    public static final String MESSAGE_INVALID_TASK_INDEX = "The task index provided is invalid.";

    public static final String MESSAGE_INTERNSHIPS_LISTED_OVERVIEW = "%1$d internships listed!";

    public static final String MESSAGE_INTERNSHIPS_STATS_OVERVIEW = "Offered: %1$d (%2$.2f%%)\n"
            + "In Progress: %3$d (%4$.2f%%)\nRejected: %5$d (%6$.2f%%)";

    public static final String MESSAGE_NO_INTERNSHIP_SELECTED = "There is no internship currently selected. "
            + "Select one before using this command.";
}
