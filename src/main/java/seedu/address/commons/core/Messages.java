package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX = "The customer index provided is invalid";
    public static final String MESSAGE_CUSTOMERS_LISTED_OVERVIEW = "%1$d customers listed!";
    public static final String MESSAGE_NO_ACTIVE_CUSTOMER = "This command requires you to open a customer first!";

    public static final String MESSAGE_CUSTOMERS_SORTED = "%1$d customers sorted in %2$s order by %3$s!";

    public static final String MESSAGE_INVALID_UNIQUE_COMPARATOR = "The sort command requires exactly one option.";

    /* Commission Messages */
    public static final String MESSAGE_COMMISSIONS_LISTED_OVERVIEW = "%1$d commissions listed!";
    public static final String MESSAGE_INVALID_COMMISSION_DISPLAYED_INDEX = "The commission index provided is invalid";
    public static final String MESSAGE_NO_ACTIVE_COMMISSION = "This command requires you to open a commission first!";
    public static final String MESSAGE_INVALID_COMMISSION_DEADLINE = "The commission deadline provided is invalid. "
            + "The input format should be YYYY-MM-DD";
    public static final String MESSAGE_INVALID_COMMISSION_FEE = "The commission fee provided is invalid";

    /* Iteration Messages */
    public static final String MESSAGE_INVALID_ITERATION_DATE = "The iteration date provided is invalid. "
            + "The input format should be YYYY-MM-DD.";
    public static final String MESSAGE_INVALID_ITERATION_DISPLAYED_INDEX = "The iteration index provided is invalid";
    public static final String MESSAGE_NONEXISTENT_IMAGE_PATH = "The image path provided does not exist";
    public static final String MESSAGE_NOT_AN_IMAGE = "The file at the given path is not an image";
    public static final String MESSAGE_INVALID_PATH = "The path provided was invalid: %1$s";

    public static final String MESSAGE_KEYWORD_EMPTY = "Keywords should not be blank or empty";

    public static final String MESSAGE_OPEN_CUSTOMER_SUCCESS = "Opened Customer: %1$s";
    public static final String MESSAGE_OPEN_COMMISSION_SUCCESS = "Opened Commission: %1$s";
}
