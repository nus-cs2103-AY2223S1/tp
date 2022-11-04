package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.storage.Storage;
import seedu.address.ui.GuiTab;

/**
 * Clears ArtBuddy.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "ArtBuddy has been cleared!";


    @Override
    public CommandResult execute(Model model, Storage storage) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        model.selectTab(GuiTab.CUSTOMER);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
