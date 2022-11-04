package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;

/**
 * Undo the last command.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undone previous command!";
    public static final String MESSAGE_FAILURE = "Maximum undo limit reached! ";
    public static final CommandType UNDO_COMMAND_TYPE = CommandType.UNDO;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        if (!model.canUndo()) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        model.saveCurrentPatientListTracker();
        model.undo();
        return new CommandResult(MESSAGE_SUCCESS, UNDO_COMMAND_TYPE);
    }
}
