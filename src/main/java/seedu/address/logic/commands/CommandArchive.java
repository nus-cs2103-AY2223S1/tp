package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command for archived tasks with hidden internal logic and the ability to be executed.
 */
public abstract class CommandArchive extends Command {

    public static final String COMMAND_WORD = "arc";
    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

}
