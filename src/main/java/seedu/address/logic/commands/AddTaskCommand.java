package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Marks a task identified by its displayed index in the GUI as completed.
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "addTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add a task to a taskList\n"
            + "Parameters: Task\n"
            + "Example: " + COMMAND_WORD + " d/<Purchase milk> D/<deadline> t/<tag>";
    public static final String MESSAGE_ADD_TASK_SUCCESS = "Task added complete: %1$s";

    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book";

    private final Task task;

    public AddTaskCommand(Task task) {
        requireNonNull(task);
        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(task)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        model.addTask(task);

        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, task));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && task.equals(((AddTaskCommand) other).task));
    }
}
