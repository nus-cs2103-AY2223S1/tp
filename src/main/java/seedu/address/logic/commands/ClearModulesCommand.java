package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_CLEAR;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the module list.
 */
public class ClearModulesCommand extends Command {

    public static final String COMMAND_WORD = "clearmodules";
    public static final String MESSAGE_SUCCESSS = "Module list has been cleared!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.getAddressBook().getTaskList().isEmpty()) {
            throw new CommandException(MESSAGE_INVALID_MODULE_CLEAR);
        }

        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESSS);
    }
}
