package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

public class AddTaskCommand extends Command{

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " : adds a task to the task list"
            + "Parameters: "
            + PREFIX_TASK_DESCRIPTION + "TASK DESCRIPTION "
            + PREFIX_TASK_DEADLINE + "TASK DEADLINE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_DESCRIPTION + "Submit documentation report to 2103T TA "
            + PREFIX_TASK_DEADLINE + "2022-10-28";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list!";

    private final Task task;

    public AddTaskCommand(Task task) {
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
