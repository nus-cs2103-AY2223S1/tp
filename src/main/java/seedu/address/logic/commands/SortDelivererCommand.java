package seedu.address.logic.commands;

/**
 * Sorts the deliverer list.
 */
public class SortDelivererCommand {

    public static final String MESSAGE_SUCCESS =
            "deliverer list has been sorted successfully";
    public static final String MESSAGE_WRONG_ATTRIBUTE =
            "%1$s is not a supported attribute in sorting deliverer list\n%2$s";
    public static final String MESSAGE_USAGE =
            "Acceptable deliverer attributes are    , address, email, location, name, phone";
}
