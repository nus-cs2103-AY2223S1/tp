package seedu.address.logic.commands;

/**
 * Sorts the order list.
 */
public class SortOrderCommand {

    public static final String MESSAGE_USAGE =
            "Acceptable order attributes are    , pricerange, duedate, price, orderstatus";
    public static final String MESSAGE_WRONG_ATTRIBUTE =
            "%1$s is not a supported attribute in sorting order list \n%2$s";
}
