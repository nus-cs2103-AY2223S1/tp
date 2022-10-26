package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Adds a task to the task panel.
 */
public class AddTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL + ": Adds a task to the task panel. "
            + "Parameters: "
            + "TITLE "
            + PREFIX_DEADLINE + "DEADLINE "
            + "[" + PREFIX_PROJECT + "PROJECT NAME] "
            + "[" + PREFIX_CONTACT + "CONTACT]...\n"
            + "Example: " + COMMAND_WORD_FULL + " "
            + "New task "
            + PREFIX_DEADLINE + "14 December 2000 "
            + PREFIX_PROJECT + "CS2103t tp "
            + PREFIX_CONTACT + "1 "
            + PREFIX_CONTACT + "2 ";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "Task with the name '%s' already exists.";

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
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            throw new CommandException(String.format(MESSAGE_DUPLICATE_TASK, toAdd.getTitle()));
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
