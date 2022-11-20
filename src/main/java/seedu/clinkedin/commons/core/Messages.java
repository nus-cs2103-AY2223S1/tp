package seedu.clinkedin.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_CANNOT_UNDO = "CLInkedIn cannot undo any further!";
    public static final String MESSAGE_CANNOT_REDO = "CLInkedIn cannot redo any further!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_STATS_DISPLAYED_OVERVIEW = "Statistics displayed!\n"
            + "Number of candidates used to calculate statistics: %1$d\n";
    public static final String MESSAGE_TAGS_STATS = "Average tags per person: %1$.2f\n"
            + "Highest number of tags a single person has: %2$.0f\n"
            + "Lowest number of tags a single person has: %3$.0f\n"
            + "Total number of tags added to displayed persons: %4$.0f";
}
