package seedu.foodrem.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command. Enter \"help\" to view all commands.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX = "The item index provided is invalid.";

    // Singular
    public static final String MESSAGE_ITEMS_LISTED_OVERVIEW = "%1$d item listed!";
    public static final String MESSAGE_ITEM_SORTED_OVERVIEW = "%1$d item sorted!";
    public static final String MESSAGE_ITEM_FILTERED_OVERVIEW = "%1$d item after filtering!";

    // Plural or Zero
    public static final String MESSAGE_ITEM_LISTED_OVERVIEW = "%1$d items listed!";
    public static final String MESSAGE_ITEMS_SORTED_OVERVIEW = "%1$d items sorted!";
    public static final String MESSAGE_ITEMS_FILTERED_OVERVIEW = "%1$d items after filtering!";

    public static final String MESSAGE_NON_POSITIVE_INDEX =
            "The index of an item should be a positive number.";
}
