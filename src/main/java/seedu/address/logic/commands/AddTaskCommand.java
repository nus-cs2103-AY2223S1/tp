package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.DateUtil.dateIsBeforeToday;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Adds a task to the taskList with specified details
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "addTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add a task to a taskList\n"
            + "Parameters: Task\n"
            + "Example: " + COMMAND_WORD + " d/Purchase milk dl/2022-10-18 t/Food";
    public static final String MESSAGE_ADD_TASK_SUCCESS = "Task added complete: %1$s";
    public static final String MESSAGE_ADD_TASK_WARNING = "WARNING: Deadline is past today!\nTask added complete: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book";

    private final Task task;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
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

        if (dateIsBeforeToday(task.getDeadline())) {
            return new CommandResult(String.format(MESSAGE_ADD_TASK_WARNING, task));
        }

        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, task));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && task.equals(((AddTaskCommand) other).task));
    }
}
