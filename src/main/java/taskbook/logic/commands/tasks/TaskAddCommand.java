package taskbook.logic.commands.tasks;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.parser.CliSyntax;
import taskbook.logic.parser.tasks.TaskCategoryParser;
import taskbook.model.Model;
import taskbook.model.person.Name;
import taskbook.model.task.Description;
import taskbook.model.task.enums.Assignment;

/**
 * Adds a task to the task book.
 */
public class TaskAddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE =
            TaskCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
            + ": Adds a task to the task book.\n"
            + "Parameters: "
            + CliSyntax.PREFIX_ASSIGN_FROM + "/" + CliSyntax.PREFIX_ASSIGN_TO + "/" + "Assigned from/Assigned to "
            + CliSyntax.PREFIX_DESCRIPTION + "DESCRIPTION";

    private final Name name;
    private final Description description;
    private final Assignment assignment;
    private final boolean isDone;

    /**
     * Creates an TaskAddCommand to add a task with the specified
     * {@code Name name}, {@code Description description} and {@code Task.Assignment assignment}
     *
     * @param name Name of the Person in the task book.
     * @param description The description for the new task.
     * @param assignment Represents task assigned to user or others.
     */
    public TaskAddCommand(Name name, Description description, Assignment assignment) {
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
