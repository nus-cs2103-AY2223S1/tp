package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;

/**
 * Clears the address book.
 */
public class ClearCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_UNDO = "Address book has been restored";
    public static final String MESSAGE_REDO = "Address book has been re-cleared!";

    private ReadOnlyAddressBook clearedAddressBook;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        clearedAddressBook = new AddressBook(model.getAddressBook());
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public CommandResult undo(Model model) throws CommandException {
        model.setAddressBook(clearedAddressBook);
        return new CommandResult((MESSAGE_UNDO));
    }

    @Override
    public CommandResult redo(Model model) throws CommandException {
        model.setAddressBook(new AddressBook());
        return new CommandResult((MESSAGE_REDO));
    }
}
