package seedu.hrpro.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.hrpro.logic.commands.exceptions.CommandException;
import seedu.hrpro.model.Model;
import seedu.hrpro.model.task.Task;

/**
 * Adds a task to the task list in HR Pro Max++.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to HR Pro Max++.\n"
            + "Parameters: "
            + PREFIX_TASK_DESCRIPTION + "TASK_DESCRIPTION "
            + PREFIX_TASK_DEADLINE + "TASK_DEADLINE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_DESCRIPTION + "Submit documentation report to 2103T TA "
            + PREFIX_TASK_DEADLINE + "2022-10-28";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list!";

    private final Task task;

    /**
     * Constructor for a AddTaskCommand.
     * @param task The task to be added into the model.
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
        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, task));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && task.equals(((AddTaskCommand) other).task));
    }
}
