package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sorts the Buyer's list based on the priority of their orders.
 */
public class SortBuyerCommand extends SortCommand {

    public static final String MESSAGE_SUCCESS = "%1$s has been sorted successfully %2$s";
    public static final String MESSAGE_USAGE = "sort";
    public static final String MESSAGE_WRONG_ATTRIBUTE = "%1$s is not a supported attribute in sorting buyer list";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
