package taskbook.logic.commands.tasks;

import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;

/**
 * Sorts the task list descriptions in reverse alphabetical order.
 */
public class TaskSortDescriptionReverseAlphabeticalCommand extends TaskSortCommand {
    public static final String MESSAGE_SORT_TYPE = " by their descriptions in reverse alphabetical order.";
    public TaskSortDescriptionReverseAlphabeticalCommand() {
        super((t1, t2) -> t1.compareByDescriptionReverseAlphabeticalTo(t2), MESSAGE_SORT_TYPE);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateSortedTaskList(super.getComparator());
        return new CommandResult(String.format(MESSAGE_SORT_TASK_SUCCESS + super.messageSortType));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskSortDescriptionReverseAlphabeticalCommand); // instanceof handles nulls
    }
}
