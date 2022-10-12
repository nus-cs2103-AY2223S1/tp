package seedu.address.logic.commands.tasks;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Command to add a new Task
 */
public class AddTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE =
            TaskCommand.COMMAND_WORD + " " + COMMAND_WORD + ": Adds a task to the address book. " + "Parameters: "
                    + PREFIX_TITLE + "TITLE " + PREFIX_GROUP + "GROUP " + "[" + PREFIX_STATUS + "STATUS]...\n"
                    + "Example: " + COMMAND_WORD + " " + PREFIX_TITLE + "Write v1 Documentation " + PREFIX_GROUP
                    + "My Group " + PREFIX_STATUS + "Completed";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book";

    public static final String MESSAGE_MISSING_GROUP = "This group does not exist in the address book";

    private final Task toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasTeam(toAdd.getParentGroup())) {
            throw new CommandException(MESSAGE_MISSING_GROUP);
        } else if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
