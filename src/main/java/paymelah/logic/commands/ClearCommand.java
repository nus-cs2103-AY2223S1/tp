package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;

import paymelah.model.AddressBook;
import paymelah.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.saveAddressBook();
        model.saveCommandMessage(MESSAGE_SUCCESS);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
