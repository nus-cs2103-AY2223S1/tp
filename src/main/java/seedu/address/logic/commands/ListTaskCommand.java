package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Lists all tasks in the task list to the user.
 */
public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all tasks %s ";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all tasks in order of most recent task added.\n"
            + COMMAND_WORD
            + " time: Lists all tasks in order of task with earliest deadline.";

    private final Comparator<Task> comparator;

    public ListTaskCommand(Comparator<Task> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        model.updateSortedTaskList(comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS, comparator.toString()), true);
    }
}
