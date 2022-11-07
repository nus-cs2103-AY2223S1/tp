package seedu.codeConnect.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.codeConnect.logic.commands.exceptions.CommandException;
import seedu.codeConnect.model.Model;

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
