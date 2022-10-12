package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TITLE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Adds a task to the task book.
 */
public class TaskCommand extends Command {
    public static final String COMMAND_WORD = "task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task. "
            + "Parameters: "
            + PREFIX_TASK_TITLE + "TITLE "
            + PREFIX_TASK_DESCRIPTION + "DESCRIPTION... \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_TITLE + "Test Title "
            + PREFIX_TASK_DESCRIPTION + "Very descriptive description ";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    private final Task toAdd;

    /**
     * Creates a TaskCommand to add the specified {@code Task}
     */
    public TaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskCommand // instanceof handles nulls
                && toAdd.equals(((TaskCommand) other).toAdd));
    }
}
