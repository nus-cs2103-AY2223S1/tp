package modtrekt.logic.commands.tasks;

import modtrekt.logic.commands.Command;
import modtrekt.logic.commands.CommandResult;
import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.model.Model;

/**
 * Lists all tasks in the task book.
 */
public class ListTasksCommand extends Command {
    public static final String COMMAND_WORD = "ls";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all tasks in the task book.";

    private final boolean areArchivedTasksShown;

    public ListTasksCommand(boolean areArchivedTasksShown) {
        this.areArchivedTasksShown = areArchivedTasksShown;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (areArchivedTasksShown) {
            model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);
            return new CommandResult("Listed all tasks, including those archived.");
        } else {
            model.updateFilteredTaskList(Model.PREDICATE_HIDE_ARCHIVED_TASKS);
            return new CommandResult("Listed all unarchived tasks.");
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListTasksCommand // instanceof handles nulls
                && areArchivedTasksShown == (((ListTasksCommand) other).areArchivedTasksShown));
    }
}
