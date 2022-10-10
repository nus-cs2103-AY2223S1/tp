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
    public static final String MESSAGE_DUPLICATE_PERSON = "This task already exists in JARVIS";

    private final Task toAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
