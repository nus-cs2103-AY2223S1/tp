package seedu.address.logic.commands.tasks;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PureCommandInterface;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

// @@author connlim

/**
 * Create a task and assign it to a group
 */
public class AddTaskCommand extends TaskCommand implements PureCommandInterface {
    public static final String SUBCOMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = TaskCommand.getFullCommand(SUBCOMMAND_WORD)
        + ": Adds a task to the address book current team. "
        + "Parameters: " + PREFIX_TITLE + "NAME " + PREFIX_DESCRIPTION + "Description";

    public static final String MESSAGE_SUCCESS = "New task have been added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists!";
    public static final String MESSAGE_NEED_TO_BE_IN_TEAM = "This task cannot be created here!";

    private final Task toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getContextContainer() == null) {
            throw new CommandException(MESSAGE_NEED_TO_BE_IN_TEAM);
        }
        toAdd.setParent(model.getContextContainer());
        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, false, toAdd);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        // creation of task do not require previous input
        return this;
    }
}
