package seedu.address.logic.commands.tasks;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a task identified using it's displayed index from the task book.
 */
public class TaskDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("Task delete command not implemented yet.");
    }
}
