package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes all completed tasks from the task list.
 */
public class CleanTasksCommand extends Command {

    public static final String COMMAND_WORD = "clean";
    public static final String MESSAGE_SUCCESS = "Completed tasks have been deleted!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.deleteCompletedTasks();
        return new CommandResult(MESSAGE_SUCCESS, true);
    }
}
