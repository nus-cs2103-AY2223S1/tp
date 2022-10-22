package seedu.address.logic.commands;

/**
 * Sorts the supplier list.
 */
public class SortSupplierCommand {

    public static final String MESSAGE_SUCCESS =
            "supplier list has been sorted successfully";
    public static final String MESSAGE_WRONG_ATTRIBUTE =
            "%1$s is not a supported attribute in sorting supplier list \n%2$s";
    public static final String MESSAGE_USAGE =
            "Acceptable supplier attributes are    , address, email, location, name, phone";
}
