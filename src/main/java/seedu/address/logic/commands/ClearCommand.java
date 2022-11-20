package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.ui.SecondaryPaneState;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearTargetPerson();
        model.clearAllReminders();
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS, SecondaryPaneState.WELCOME);
    }
}
