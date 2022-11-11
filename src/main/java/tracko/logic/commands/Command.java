package tracko.logic.commands;

import tracko.logic.commands.exceptions.CommandException;
import tracko.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {


    protected Command() {}

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    public boolean isMultiLevelCommand() {
        return this instanceof MultiLevelCommand;
    }

    public boolean isAwaitingInput() {
        return false;
    }

}
