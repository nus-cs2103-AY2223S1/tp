package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ReadOnlyAddressBook pastAddressBook = (ReadOnlyAddressBook) model.getAddressBook().clone();
        model.setAddressBook(new AddressBook());
        UndoCommand.saveBeforeMod(this, pastAddressBook, model.getAddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
