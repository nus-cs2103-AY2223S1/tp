package jarvis.logic.commands;

import static jarvis.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static jarvis.logic.parser.CliSyntax.PREFIX_TASK_DESC;
import static java.util.Objects.requireNonNull;

import jarvis.logic.commands.exceptions.CommandException;
import jarvis.model.Model;
import jarvis.model.Task;


/**
 * Adds a task to the task book.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to JARVIS.\n"
            + "Parameters: "
            + PREFIX_TASK_DESC + "TASK_DESC "
            + "[" + PREFIX_DEADLINE + "DEADLINE] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TASK_DESC + "Mark missions " + PREFIX_DEADLINE + "2022-10-09";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in JARVIS";

    private final Task taskToAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        taskToAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(taskToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(taskToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && taskToAdd.equals(((AddTaskCommand) other).taskToAdd));
    }
}
