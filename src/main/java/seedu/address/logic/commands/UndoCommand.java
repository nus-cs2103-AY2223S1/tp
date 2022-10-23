package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undoes the most recent command.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Command has be undone!";

    public static final String MESSAGE_UNDO_INVALID = "Unable to undo command";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canUndoAddressBook()) {
            throw new CommandException(MESSAGE_UNDO_INVALID);
        }

        model.undoAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
