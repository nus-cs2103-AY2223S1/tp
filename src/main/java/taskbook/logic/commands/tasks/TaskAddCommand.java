package taskbook.logic.commands.tasks;

import static taskbook.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;
import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.tag.Tag;
import taskbook.model.task.Deadline;
import taskbook.model.task.Description;
import taskbook.model.task.Event;
import taskbook.model.task.Task;
import taskbook.model.task.Todo;
import taskbook.model.task.enums.Assignment;

/**
 * Abstract command to add a general task to the task book.
 */
public abstract class TaskAddCommand extends Command {

    public static final String MESSAGE_PERSON_NOT_FOUND = "Person not found in task book!";

    public static final String MESSAGE_DUPLICATE_TASK_FAILURE = "Task cannot be added.\n"
            + "Task already exists in task book.";

    private final Name name;
    private final Description description;
    private final Assignment assignment;
    private final boolean isDone;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Creates a TaskAddCommand to add a task with the specified
     * {@code Name name}, {@code Description description} and {@code Task.Assignment assignment}.
     *
     * @param name Name of the Person in the task book.
     * @param description The description for the new task.
     * @param assignment Represents task assigned to user or others.
     */
    protected TaskAddCommand(Name name, Description description, Assignment assignment) {
        requireAllNonNull(name, description, assignment);
        this.name = name;
        this.description = description;
        this.assignment = assignment;
        this.isDone = false;
    }

    /**
     * Creates a TaskAddCommand to add a task with the specified
     * {@code Name name}, {@code Description description} and {@code Task.Assignment assignment}.
     *
     * @param name Name of the Person in the task book.
     * @param description The description for the new task.
     * @param assignment Represents task assigned to user or others.
     * @param tags Tags assigned to task.
     */
    protected TaskAddCommand(Name name, Description description, Assignment assignment, Set<Tag> tags) {
        requireAllNonNull(name, description, assignment, tags);
        this.name = name;
        this.description = description;
        this.assignment = assignment;
        this.isDone = false;
        this.tags.addAll(tags);
    }

    public Task createTodo() {
        return new Todo(name, assignment, description, isDone, tags);
    }

    public Task createDeadline(LocalDate date) {
        return new Deadline(name, assignment, description, isDone, date , tags);
    }

    public Task createEvent(LocalDate date) {
        return new Event(name, assignment, description, isDone, date , tags);
    }

    /**
     * Checks whether a person with specified name exist in the task book model,
     * throws an exception if no such person exist.
     *
     * @param model Task book model to find person.
     * @throws CommandException Person not found exception thrown if no such person exist.
     */
    public void checkPersonNameExist(Model model) throws CommandException {
        if (name.fullName.equalsIgnoreCase(Name.SELF.fullName)) {
            return;
        }
        Person personToAddTask = model.findPerson(name);
        if (personToAddTask == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TaskAddCommand)) {
            return false;
        }

        TaskAddCommand otherCommand = (TaskAddCommand) other;
        return otherCommand.name.equals(name)
                && otherCommand.description.equals(description)
                && otherCommand.assignment.equals(assignment)
                && otherCommand.isDone == isDone
                && otherCommand.tags.equals(tags);
    }
}
