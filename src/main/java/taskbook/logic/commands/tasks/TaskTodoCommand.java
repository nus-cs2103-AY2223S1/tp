package taskbook.logic.commands.tasks;

import static java.util.Objects.requireNonNull;
import static taskbook.logic.parser.CliSyntax.PREFIX_ASSIGN_FROM;
import static taskbook.logic.parser.CliSyntax.PREFIX_ASSIGN_TO;
import static taskbook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static taskbook.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.parser.tasks.TaskCategoryParser;
import taskbook.model.Model;
import taskbook.model.person.Name;
import taskbook.model.tag.Tag;
import taskbook.model.task.Description;
import taskbook.model.task.Task;
import taskbook.model.task.enums.Assignment;

/**
 * Adds a to-do to the task book.
 */
public class TaskTodoCommand extends TaskAddCommand {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE =
            TaskCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
            + ": Adds a todo to the task book.\n"
            + "\n"
            + "Parameters:\n"
            + PREFIX_ASSIGN_FROM + "NAME " + PREFIX_DESCRIPTION + "DESCRIPTION " + "[" + PREFIX_TAG + "TAG]...\n"
            + PREFIX_ASSIGN_TO + "NAME " + PREFIX_DESCRIPTION + "DESCRIPTION " + "[" + PREFIX_TAG + "TAG]...\n";

    public static final String MESSAGE_SUCCESS = "New todo added: %1$s";

    /**
     * Creates a TaskTodoCommand to add a task with the specified
     * {@code Name name}, {@code Description description} and {@code Task.Assignment assignment}.
     *
     * @param name Name of the Person in the task book.
     * @param description The description for the new to-do.
     * @param assignment Represents to-do assigned to user or others.
     */
    public TaskTodoCommand(Name name, Description description, Assignment assignment) {
        super(name, description, assignment);
    }

    /**
     * Creates a TaskTodoCommand to add a task with the specified
     * {@code Name name}, {@code Description description} and {@code Task.Assignment assignment}.
     *
     * @param name Name of the Person in the task book.
     * @param description The description for the new to-do.
     * @param assignment Represents to-do assigned to user or others.
     * @param tags Tags assigned to task.
     */
    public TaskTodoCommand(Name name, Description description, Assignment assignment, Set<Tag> tags) {
        super(name, description, assignment, tags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        checkPersonNameExist(model);

        Task newTask = createTodo();
        if (model.hasTask(newTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK_FAILURE);
        }

        model.addTask(newTask);
        model.commitTaskBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, newTask));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TaskTodoCommand)) {
            return false;
        }

        return super.equals(other);
    }
}
