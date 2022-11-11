package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Adds a task to the task panel.
 */
public class AddTaskCommand extends TaskCommand {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD_FULL + ": Adds a task to the task panel. \n"
            + "Parameters: "
            + "TITLE "
            + "Example: " + COMMAND_WORD_FULL + " "
            + "New task";

    public static final String MESSAGE_SUCCESS = "Successfully added new task: %s";
    public static final String MESSAGE_DUPLICATE_TASK = "Task with the name '%s' already exists.";

    private final Title title;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Title title) {
        requireNonNull(title);
        this.title = title;
    }

    /**
     * Creates an AddTaskCommand for the purpose of testing.
     */
    public AddTaskCommand(Task task) {
        title = task.getTitle();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Task toAdd = new Task(title);

        if (model.hasTask(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_TASK, toAdd.getTitle()));
        }

        model.addTask(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, title));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && title.equals(((AddTaskCommand) other).title));
    }

}
