package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Lists all commissions under the selected customer in the address book to the user.
 */
public class ListCommissionCommand extends Command {
    public static final String COMMAND_WORD = "listcom";
    public static final String MESSAGE_SUCCESS = "Displayed the unfiltered list of commissions.";

    @Override
    public CommandResult execute(Model model, Storage storage) {
        requireNonNull(model);
        model.updateFilteredCommissionList(Model.PREDICATE_SHOW_ALL_COMMISSIONS);
        model.selectTab(GuiTab.COMMISSION);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
