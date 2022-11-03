package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The index provided is invalid";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_UID = "The person uid provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INVALID_CATEGORY = "Invalid category detected!";
    public static final String MESSAGE_UPDATECONTACT_INVALID_CATEGORY = "Contact info can only be set for patients.";
    public static final String MESSAGE_UPDATECONTACT_INVALID_CONTACT_CATEGORY =
            "Contact must be a physician (category D) or next of kin (category K)";


}
