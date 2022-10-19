package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;

/**
 * Undo the last command.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_FAILURE = "Maximum undo limit reached! ";
    public static final String MESSAGE_SUCCESS = "Undone previous command!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.canUndo()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undo();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
