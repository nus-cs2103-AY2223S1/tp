package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The index provided is invalid";

    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";

    public static final String MESSAGE_TUTORS_LISTED_OVERVIEW = "%1$d tutors listed!";

    public static final String MESSAGE_TUITIONCLASSES_LISTED_OVERVIEW = "%1$d tuition classes listed!";

    public static final String MESSAGE_WRONG_LIST_FOR_COMMAND_USAGE = "Wrong list to execute this command!";


    // Find command
    public static final String MESSAGE_KEYWORDS_WITHOUT_PREFIX = "You have specified some "
            + "keywords without any prefixes.\n%1$s";

    public static final String MESSAGE_INVALID_PREFIX = "Invalid prefix/keyword format!\n"
            + "One of 2 things could have happened:\n"
            + "1. You have specified an invalid prefix.\n"
            + "2. A specified keyword contains the character '/'.\n\n%1$s";

    public static final String MESSAGE_EMPTY_PREFIX = "You have specified a prefix without any keywords.\n%1$s";

    public static final String MESSAGE_NO_PREFIX_SPECIFIED = "You did not specify any prefixes to find by.\n%1$s";

    public static final String MESSAGE_PREFIX_NOT_FOR_STUDENT = "You have specified a prefix that is"
            + " not related to a student.\n%1$s";

    public static final String MESSAGE_PREFIX_NOT_FOR_TUTOR = "You have specified a prefix that is "
            + "not related to a tutor.\n%1$s";

    public static final String MESSAGE_PREFIX_NOT_FOR_TUITIONCLASS = "You have specified a prefix that is "
            + "not related to a tuition class.\n%1$s";
}
