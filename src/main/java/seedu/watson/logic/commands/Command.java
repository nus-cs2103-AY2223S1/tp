package seedu.watson.logic.commands;

import seedu.watson.logic.commands.exceptions.CommandException;
import seedu.watson.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    /**
     * For debugging purposes only.
     *
     * @return the command string representation.
     */
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
