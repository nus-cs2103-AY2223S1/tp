package seedu.address.logic.commands.listcommands;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all orders to the user.
 */
public class ListOrderCommand extends ListCommand {
    public static final String MESSAGE_SUCCESS = "Listed all orders";

    @Override
    public CommandResult execute(Model model) {
        updateFilteredList(model);
        model.switchToOrderList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        return object instanceof ListOrderCommand;
    }
}
