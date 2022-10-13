package taskbook.logic.commands.tasks;

import static java.util.Objects.requireNonNull;
import static taskbook.commons.util.CollectionUtil.requireAllNonNull;
import static taskbook.logic.parser.CliSyntax.PREFIX_ASSIGN_FROM;
import static taskbook.logic.parser.CliSyntax.PREFIX_ASSIGN_TO;
import static taskbook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.parser.tasks.TaskCategoryParser;
import taskbook.model.Model;
import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.task.Description;
import taskbook.model.task.Task;
import taskbook.model.task.Todo;
import taskbook.model.task.enums.Assignment;

/**
 * Adds a task to the task book.
 */
public class TaskAddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE =
            TaskCategoryParser.CATEGORY_WORD + " " + COMMAND_WORD
            + ": Adds a task to the task book.\n"
            + "Parameters:\n"
            + PREFIX_ASSIGN_FROM + "NAME " + PREFIX_DESCRIPTION + "DESCRIPTION\n"
            + PREFIX_ASSIGN_TO + "NAME " + PREFIX_DESCRIPTION + "DESCRIPTION";
    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person not found in task book!";

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
        requireAllNonNull(name, description, assignment);

        this.name = name;
        this.description = description;
        this.assignment = assignment;
        this.isDone = false;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToAddTask = model.findPerson(name);
        if (personToAddTask == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }

        Task newTask = new Todo(personToAddTask, assignment, description, isDone);
        model.addTask(newTask);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newTask));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof TaskAddCommand) {
            TaskAddCommand otherCommand = (TaskAddCommand) other;
            return otherCommand.name.equals(name)
                    && otherCommand.description.equals(description)
                    && otherCommand.assignment.equals(assignment)
                    && otherCommand.isDone == isDone;
        }

        return false;
    }
}
