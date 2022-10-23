package seedu.clinkedin.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_STATS_DISPLAYED_OVERVIEW = "Statistics displayed!\n"
            + "Number of persons used to calculate statistics: %5$d\n"
            + "Average tags per person: %1$.2f\n"
            + "Highest number of tags a single person has: %2$.0f\n"
            + "Lowest number of tags a single person has: %3$.0f\n"
            + "Total number of tags added in ClInkedIn: %4$.0f";
}
