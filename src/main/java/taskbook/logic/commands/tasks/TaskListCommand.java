package taskbook.logic.commands.tasks;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;

/**
 * Lists all tasks in the task book to the user.
 */
public class TaskListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("Task list command not implemented yet.");
    }
}
