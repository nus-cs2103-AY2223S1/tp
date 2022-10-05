package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;

public class AddTCommand extends Command {

    public static final String COMMAND_WORD = "addT";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the TaskList. "
            + "Parameters: "
            + PREFIX_TASK_DESCRIPTION + "DESCRIPTION "
            + PREFIX_TASK_DEADLINE + "DEADLINE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_DESCRIPTION + "buy milk "
            + PREFIX_TASK_DEADLINE + "12-09-2022";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_MISSING_DESCRIPTION = "A task must have a description";

    private final Task newTask;

    public AddTCommand(Task task) {
        requireNonNull(task);
        newTask = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addT(newTask);
        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, newTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTCommand // instanceof handles nulls
                && newTask.equals(((AddTCommand) other).newTask));
    }

}
