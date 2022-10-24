package taskbook.logic.commands.tasks;

import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;

/**
 * Sorts the task list in the order of the tasks were added to Task Book.
 */
public class TaskSortAddedChronologicalCommand extends TaskSortCommand {
    public static final String MESSAGE_SORT_TYPE = " by time added into Task Book";
    public TaskSortAddedChronologicalCommand() {
        super(null, MESSAGE_SORT_TYPE);
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.resetSortedTaskList();
        return new CommandResult(String.format(MESSAGE_SORT_TASK_SUCCESS + super.messageSortType));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskSortAddedChronologicalCommand); // instanceof handles nulls
    }
}
