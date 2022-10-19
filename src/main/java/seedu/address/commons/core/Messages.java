package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_PERSONS_SUCCESS = "Deleted person(s) in the range!";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String INCOMPLETE_COMMAND = "Command is incomplete, specify either "
            + "appts or patients behind the command word";
    public static final String START_DATE_AFTER_END_DATE = "The start date entered should not be after the end date!";

    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX =
            "The appointment index provided is invalid";

    public static final String MESSAGE_RESULTS_LISTED_OVERVIEW = "%1$d person(s) and %2$d appointment(s) listed!";

}
