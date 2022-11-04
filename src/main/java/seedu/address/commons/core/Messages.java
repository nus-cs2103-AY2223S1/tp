package seedu.address.commons.core;

import seedu.address.logic.parser.CliSyntax;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    public static final String MESSAGE_INVALID_AMBIGUOUS_TITLE = "There is more than 1 note with %s in their title!\n"
            + "Please use a more unique specifier or use indices to edit.";

    public static final String MESSAGE_INVALID_TITLE = "There are no notes with %s in their titles in the list!";

    public static final String MESSAGE_INVALID_NOTE_DISPLAYED_INDEX = "The note index provided is invalid";

    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";

    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";

    public static final String MESSAGE_NOTES_LISTED_OVERVIEW = "%1$d notes listed!";

    public static final String MESSAGE_NUMBER_TOO_SHORT = "Number to check must be at least 2 digits";

    public static final String MESSAGE_INVALID_KEYWORD = "Keyword is invalid,keyword cannot contain special characters";

    public static final String MESSAGE_INVALID_AMBIGUOUS_NAME = "There is more than 1 person with %s in their name!\n"
            + "Please use a more unique specifier or use indices to edit.";

    public static final String MESSAGE_INVALID_NAME = "No names matched the given keywords %s!";

    public static final String MESSAGE_INVALID_NON_POSITIVE_INDEX = "You may not specify non-positive indices!";

    public static final String AMOUNT_NOT_SPECIFIED = "No amount to was specified to edit the loan with.\n"
            + "Please use " + CliSyntax.PREFIX_LOAN_AMOUNT + " to specify a change in loan amount!";

    public static final String REASON_NOT_SPECIFIED = "A reason must be given to change loan amounts.\n"
            + "Please use " + CliSyntax.PREFIX_LOAN_REASON + " to specify a reason to change the loan value!";

    public static final String OUT_OF_BOUNDS = "The index given to be inspected must be within "
            + "the bounds of the list!";

    public static final String NOT_AN_INTEGER = "The index given was not an integer value";

    public static final String TOTAL_LOAN_OUT_OF_BOUNDS = "Operation refused as the total loan amount"
            + " will be out of bounds.";

    public static final String AMBIGUOUS_NAME_INSPECT_FIRST = "There was more than one person of that name found.\n"
            + "Showing the first person matching the given name.\n"
            + "Note that inspection works only on the list you are currently viewing.\n"
            + "Perhaps you would like to perform a find operation with keywords using "
            + "the find command to narrow your search?";

    public static final String MESSAGE_INVALID_NAME_INSPECT = "No names matched the given keywords %s!\n"
            + "Note that inspection works only on the list you are currently viewing.\n"
            + "Perhaps you would like to list out all persons with the list command "
            + "to widen your search?";
}
