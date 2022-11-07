package seedu.uninurse.logic.commands;

import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {
    /**
     * Executes the command and returns the result message.
     *
     * @param model The Model which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    /**
     * Checks whether the command is undo-able.
     *
     * @return true if command is undo-able.
     */
    public boolean isUndoable() {
        return false;
    }
}
