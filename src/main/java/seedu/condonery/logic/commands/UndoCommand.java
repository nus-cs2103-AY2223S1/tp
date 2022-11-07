package seedu.condonery.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.logic.commands.exceptions.EmptyQueueException;
import seedu.condonery.model.Model;

/**
 * Undo the previous command.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Successfully undid previous command.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.undoCommand();
            return new CommandResult(MESSAGE_SUCCESS);

        } catch (EmptyQueueException ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
