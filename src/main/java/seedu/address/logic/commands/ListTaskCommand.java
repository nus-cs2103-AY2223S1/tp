package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.task.DeadlineComparator;
import seedu.address.model.task.Task;

/**
 * Lists all persons in the address book to the user.
 */
public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_LIST_DEFAULT_SUCCESS = "Listed all tasks in order of most recent task added.";

    public static final String MESSAGE_LIST_DEADLINE_SUCCESS =
            "Listed all tasks in order of task with earliest deadline.";

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
        String message = this.getMessage();
        return new CommandResult(message, true);
    }

    /**
     * Returns success message depending on type of listing of tasks executed.
     */
    private String getMessage() {
        if (comparator instanceof DeadlineComparator) {
            return MESSAGE_LIST_DEADLINE_SUCCESS;
        } else {
            return MESSAGE_LIST_DEFAULT_SUCCESS;
        }
    }
}
