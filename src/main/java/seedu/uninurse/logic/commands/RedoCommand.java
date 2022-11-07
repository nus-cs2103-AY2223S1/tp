package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;

/**
 * Undo the last undo command.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redone the following command:\n\n";
    public static final String MESSAGE_FAILURE = "No command to redo!";
    public static final CommandType COMMAND_TYPE = CommandType.REDO;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        if (!model.canRedo()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        CommandResult redoneCommandResult = model.redo();
        model.saveCurrentPersonListTracker();
        return new CommandResult(MESSAGE_SUCCESS + redoneCommandResult.getFeedbackToUser(), COMMAND_TYPE);
    }
}
