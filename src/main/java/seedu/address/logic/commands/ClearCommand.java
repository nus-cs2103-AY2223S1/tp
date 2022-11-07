package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_SHORTCUT = "cl";
    public static final String MESSAGE_SUCCESS = "FABook has been cleared!";
    public static final String MESSAGE_EMPTY_ADDRESSBOOK = "FABook is already empty";
    public static final String MESSAGE_UNDO = "FABook has been restored";
    public static final String MESSAGE_REDO = "FABook has been re-cleared!";

    private ReadOnlyAddressBook clearedAddressBook;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        clearedAddressBook = new AddressBook(model.getAddressBook());
        if (clearedAddressBook.getPersonList().isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_ADDRESSBOOK);
        }
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public CommandResult undo(Model model) {
        model.setAddressBook(clearedAddressBook);
        return new CommandResult((MESSAGE_UNDO));
    }

    @Override
    public CommandResult redo(Model model) {
        model.setAddressBook(new AddressBook());
        return new CommandResult((MESSAGE_REDO));
    }
}
