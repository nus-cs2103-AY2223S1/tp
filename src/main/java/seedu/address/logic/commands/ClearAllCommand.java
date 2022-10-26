package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears task, exam and module lists.
 */
public class ClearAllCommand extends Command {

    public static final String COMMAND_WORD = "clearall";
    public static final String MESSAGE_SUCCESSS = "Task, exam and module lists have been cleared!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESSS);
    }
}
