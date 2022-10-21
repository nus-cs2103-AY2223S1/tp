package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sorts the Buyer's list.
 */
public class SortBuyerCommand extends SortCommand {

    public static final String MESSAGE_SUCCESS = "buyer list has been sorted successfully based on attribute(s) %1$s";
    public static final String MESSAGE_USAGE =
            "Acceptable buyer attributes are    , address, email, location, name, phone";
    public static final String MESSAGE_WRONG_ATTRIBUTE =
            "%1$s is not a supported attribute in sorting buyer list \n%2$s";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
