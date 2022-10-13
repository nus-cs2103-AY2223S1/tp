package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.MainPanelName;

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
     * To indicate whether the command can be executed on given panel.
     * By default, every command can be executed at any panel.
     *
     * @param name of the given panel.
     * @return boolean to indicate whether the command can be executed
     */
    public abstract boolean canExecuteAt(MainPanelName name);

}
