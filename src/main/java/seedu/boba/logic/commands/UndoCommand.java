package seedu.boba.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.exceptions.NextStateNotFoundException;
import seedu.boba.model.exceptions.PreviousStateNotFoundException;

/**
 * Returns bobaBot to the state before executing the previous command.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Returns bobaBot to the state before executing the previous command.\n"
            + "Parameters: No Parameters\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_UNDO_SUCCESS = "Successfully returned to previous state!";

    public UndoCommand() {
    }

    @Override
    public CommandResult execute(BobaBotModel bobaBotModel) throws CommandException {
        requireNonNull(bobaBotModel);

        try {
            bobaBotModel.undoBobaBot();
        } catch (PreviousStateNotFoundException | NextStateNotFoundException e) {
            throw new CommandException(e.getMessage());
        }

        return new CommandResult(MESSAGE_UNDO_SUCCESS, false, false, true, false, false);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof UndoCommand;
    }
}
