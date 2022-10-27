package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import javafx.collections.ObservableList;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.model.Model;
import seedu.address.model.TaskPanel;
import seedu.address.model.task.Task;
import javafx.collections.transformation.FilteredList;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.AssignedToContactsPredicate;
import seedu.address.model.task.ContainsProjectsPredicate;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.DeadlineIsAfterPredicate;
import seedu.address.model.task.DeadlineIsBeforePredicate;

import seedu.address.model.task.TitleContainsKeywordPredicate;

import java.util.Comparator;

/**
 * Clears the task panel.
 */
public class SortTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "sort";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL
            + ": Sort the tasks by their deadlines.\n"
            + "Parameters: \n"
            + "Example: " + COMMAND_WORD_FULL;

    public static final String MESSAGE_SUCCESS = "Task Panel has been sorted by deadlines.";

    public static final DeadlineComparator deadlineComparator = new DeadlineComparator();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        ObservableList<Task> currentTasks = model.getFilteredTaskList();
        currentTasks = currentTasks.sorted(deadlineComparator);
        model.setFilteredTaskList(currentTasks);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.logic.commands.task.SortTaskCommand); // instanceof handles nulls
    }
}

class DeadlineComparator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        if (task1.getDeadline().isAfter(task2.getDeadline())) {
            return 1;
        }
        else if (task1.getDeadline().isBefore(task2.getDeadline())) {
            return -1;
        }
        return 0;
    }
}
