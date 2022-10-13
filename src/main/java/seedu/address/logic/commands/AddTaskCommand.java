package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.team.Task;


/**
 * Adds a task to the current team.
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "add_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Adds a task to the current team.\n"
        + "Parameters: name of task (cannot be empty and cannot begin with a space) \n"
        + "Example: " + COMMAND_WORD + " merge PR#12";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "Added Task: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the team";
    public static final String MESSAGE_TASK_NAME_FORMAT_ERROR = "Task name cannot be empty";

    private final Task task;

    /**
     * Returns a command that adds a task to the current team.
     * @param taskName the name of the task to be added.
     * @throws ParseException when the task name does not follow the format specified.
     */
    public AddTaskCommand(String taskName) {
        this.task = new Task(taskName);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getTeam().hasTask(task)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        model.getTeam().addTask(task);
        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, task));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddTaskCommand // instanceof handles nulls
            && task.equals(((AddTaskCommand) other).task)); // state check
    }
}
