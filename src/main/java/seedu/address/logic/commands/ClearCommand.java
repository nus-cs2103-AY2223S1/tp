package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear-confirm";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_CONFIRMATION = "You are attempting to clear all addresses in the memory."
            + " Add '-confirm' to end of command to clear all addresses.";
    public static final String MESSAGE_EMPTY_LIST = "Address book is already empty!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getAddressBookSize() == 0) {
            throw new CommandException(MESSAGE_EMPTY_LIST);
        }
        model.setAddressBook(new AddressBook());
        model.updatePieChart();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
