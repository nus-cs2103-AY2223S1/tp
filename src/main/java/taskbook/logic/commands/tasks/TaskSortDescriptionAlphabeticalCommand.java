package taskbook.logic.commands.tasks;

import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;

/**
 * Sorts the task list descriptions in alphabetical order.
 */
public class TaskSortDescriptionAlphabeticalCommand extends TaskSortCommand {
    public static final String MESSAGE_SORT_TYPE = " by their descriptions in alphabetical order.";
    public TaskSortDescriptionAlphabeticalCommand() {
        super((t1, t2) -> t1.compareByDescriptionAlphabeticalTo(t2), MESSAGE_SORT_TYPE);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateSortedTaskList(super.getComparator());
        return new CommandResult(String.format(MESSAGE_SORT_TASK_SUCCESS + super.messageSortType));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskSortDescriptionAlphabeticalCommand); // instanceof handles nulls
    }
}
