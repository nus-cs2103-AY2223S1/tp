package seedu.address.logic.commands.tasks;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.tasks.TaskCategoryParser;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.task.enums.Assignment;

/**
 * Adds a task to the task book.
 */
public class TaskAddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE =
            TaskCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
            + ": Adds a task to the task book.\n"
            + "Parameters: "
            + PREFIX_ASSIGNOR + "/" + PREFIX_ASSIGNEE + "/" + "ASSIGNOR/ASSIGNEE "
            + PREFIX_DESCRIPTION + "DESCRIPTION";

    private final Name name;
    private final String description;
    private final Assignment assignment;
    private final boolean isDone;

    /**
     * Creates an TaskAddCommand to add a task with the specified
     * {@code Name name}, {@code String description} and {@code Task.Assignment assignment}
     *
     * @param name Name of the Person in the task book.
     * @param description The description for the new task.
     * @param assignment Represents task assigned to user or others.
     */
    public TaskAddCommand(Name name, String description, Assignment assignment) {
        this.name = name;
        this.description = description;
        this.assignment = assignment;
        this.isDone = false;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Humphrey, you can use the model to query the AddressBook (to be renamed)
        // for the Person using Name.
        throw new CommandException("Task add command not implemented yet.");
    }
}
