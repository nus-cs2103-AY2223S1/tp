package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;

/**
 * Undo the last command.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undone the following command:\n\n";
    public static final String MESSAGE_FAILURE = "Maximum undo limit reached! ";
    public static final CommandType COMMAND_TYPE = CommandType.UNDO;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        if (!model.canUndo()) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        model.saveCurrentPersonListTracker();
        CommandResult undoneCommandResult = model.undo();
        return new CommandResult(MESSAGE_SUCCESS + undoneCommandResult.getFeedbackToUser(), COMMAND_TYPE);
    }
}
