package taskbook.logic.commands.tasks;

import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;

/**
 * Sorts the list by the description's alphabetical order.
 */
public class TaskSortAddedChronologicalCommand extends TaskSortCommand {
    public TaskSortAddedChronologicalCommand() {
        super(null);
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateSort(super.getComparator());
        return new CommandResult(String.format(MESSAGE_SORT_TASK_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskSortAddedChronologicalCommand); // instanceof handles nulls
    }
}
