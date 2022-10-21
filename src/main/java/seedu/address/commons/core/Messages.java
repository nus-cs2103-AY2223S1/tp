package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_NO_UNIQUE_PREFIX_IDENTIFIER =
            "Please use ONLY ONE phone or email as identifier \n%1$s";
    public static final String MESSAGE_BOTH_EMAIL_AND_PHONE =
            "Please use only EMAIL or PHONE as identifier, NOT BOTH! \n%1$s";
    public static final String MESSAGE_EMPTY_EMAIL_AND_PHONE = "No EMAIL or PHONE identifier found in command! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_INFORMATION = "No customer matching input details!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";

}
