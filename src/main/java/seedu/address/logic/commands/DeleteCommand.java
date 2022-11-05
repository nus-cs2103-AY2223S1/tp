package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a person or the user from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the user or the person identified by the index number used in the displayed person list.\n"
            + "Parameters: user / INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " user / " + COMMAND_WORD + " 1";

    public DeleteCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.commitAddressBook();
        return new CommandResult("");
    }
}
