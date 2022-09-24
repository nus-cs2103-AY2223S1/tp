package seedu.address.logic.commands.tasks;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all tasks in the task book to the user.
 */
public class TaskListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("Task list command not implemented yet.");
    }
}
