package seedu.address.logic.commands.listcommands;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class ListBuyerCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS = "Listed all buyers";

    @Override
    public CommandResult execute(Model model) {
        updateFilteredList(model);
        model.switchToBuyerList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        return object instanceof ListBuyerCommand;
    }
}
