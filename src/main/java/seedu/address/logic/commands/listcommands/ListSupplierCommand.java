package seedu.address.logic.commands.listcommands;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class ListSupplierCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS = "Listed all suppliers";

    @Override
    public CommandResult execute(Model model) {
        updateFilteredList(model);
        model.switchToSupplierList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        return object instanceof ListSupplierCommand;
    }
}
