package seedu.address.commons.core;

import seedu.address.model.person.Name;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_NAME = "The person name provided has no match";
    public static final String MESSAGE_MULTIPLE_PERSON_DISPLAYED_NAME = "The person name provided matches multiple"
            + " entries! Please delete by index instead or enter their full name.";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";

    /**
     * Generates a default happy birthday message for each Person.
     */
    public static String generateHappyBirthdayMessage(Name name) {
        return "Wish " + name + " Happy Birthday!";
    }
}
