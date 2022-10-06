package seedu.address.logic.commands.tasks;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGN_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGN_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.tasks.TaskCategoryParser;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
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
            + PREFIX_ASSIGN_FROM + "/" + PREFIX_ASSIGN_TO + "/" + "Assigned from/Assigned to "
            + PREFIX_DESCRIPTION + "DESCRIPTION";

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
        requireNonNull(name);
        requireNonNull(description);
        requireNonNull(assignment);
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

        Task newTask = new Task(personToAddTask, this.assignment, this.description, this.isDone);
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
