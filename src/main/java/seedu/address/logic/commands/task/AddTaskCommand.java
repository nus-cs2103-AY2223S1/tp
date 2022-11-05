package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Adds a task to the TaskList.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addT";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the TaskList.\n"
            + "Parameters: "
            + PREFIX_TASK_DESCRIPTION + "DESCRIPTION "
            + PREFIX_TASK_DEADLINE + "DEADLINE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_DESCRIPTION + "buy milk "
            + PREFIX_TASK_DEADLINE + "12-09-2022";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book";

    private final Task newTask;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        newTask = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(newTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(newTask);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, newTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && newTask.equals(((AddTaskCommand) other).newTask));
    }

}
