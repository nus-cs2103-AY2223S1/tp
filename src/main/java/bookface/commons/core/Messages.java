package bookface.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_BOOK_DISPLAYED_INDEX = "The book index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_BOOKS_LISTED_OVERVIEW = "%1$d books listed!";
    public static final String MESSAGE_DUPLICATE_BOOK = "This book is already in the records.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the records.";
    public static final String MESSAGE_INVALID_DATE_PARSE = "The input after loan USER_INDEX, "
            + "BOOK_INDEX [DUE_DATE] is unable to be parsed as date. Please follow the format \n%1$s";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "The date and month should be valid values.";
    public static final String MESSAGE_BOOK_STILL_ON_LOAN = "Book cannot be deleted; it is currently "
            + "loaned out to someone!";
    public static final String MESSAGE_PERSON_HAS_LOANS = "Person cannot be deleted; there are loans that "
            + "are not settled!";
}
