package taskbook.logic.commands.tasks;

import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;

/**
 * Sorts the task list in chronological order of the tasks' dates.
 * Tasks without dates are left at the end of the list, and in no guaranteed order.
 */
public class TaskSortDateReverseChronologicalCommand extends TaskSortCommand {
    public static final String MESSAGE_SORT_TYPE = " by descending dates associated to tasks.\n"
            + "Tasks with no date are at the end of the list in no particular order.";
    public TaskSortDateReverseChronologicalCommand() {
        super((t1, t2) -> t1.compareByReverseChronologicalDateTo(t2), MESSAGE_SORT_TYPE);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateSortedTaskList(super.getComparator());
        return new CommandResult(String.format(MESSAGE_SORT_TASK_SUCCESS + super.messageSortType));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskSortDateReverseChronologicalCommand); // instanceof handles nulls
    }
}
