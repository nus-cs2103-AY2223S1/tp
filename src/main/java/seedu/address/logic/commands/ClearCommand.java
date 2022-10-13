package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.ClassStorage;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ReadOnlyAddressBook addressBook = new AddressBook();
        model.setAddressBook(addressBook);
        ClassStorage classStorage = new ClassStorage(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
