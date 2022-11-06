package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Lists all commissions under the selected customer in the address book to the user.
 */
public class AllCommissionCommand extends Command {
    public static final String COMMAND_WORD = "allcom";
    public static final String MESSAGE_SUCCESS = "Listed all commissions of all customers";

    @Override
    public CommandResult execute(Model model, Storage storage) {
        requireNonNull(model);
        model.selectCustomer(null);
        model.selectTab(GuiTab.COMMISSION);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
