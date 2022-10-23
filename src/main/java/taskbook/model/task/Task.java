package taskbook.model.task;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import taskbook.commons.util.CollectionUtil;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.tag.Tag;
import taskbook.model.task.enums.Assignment;

/**
 * Represents a Task in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Task {

    private final Name name;
    private final Assignment assignment;
    private final Description description;
    private final boolean isDone;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    protected Task(Person person, Assignment assignment, Description description, boolean isDone) {
        CollectionUtil.requireAllNonNull(person, assignment, description, isDone);
        this.name = person.getName();
        this.assignment = assignment;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Every field must be present and not null.
     */
    protected Task(Name name, Assignment assignment, Description description, boolean isDone) {
        CollectionUtil.requireAllNonNull(name, assignment, description, isDone);
        this.name = name;
        this.assignment = assignment;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Every field must be present and not null.
     */
    protected Task(Person person, Assignment assignment, Description description, boolean isDone, Set<Tag> tags) {
        CollectionUtil.requireAllNonNull(person, assignment, description, isDone, tags);
        this.name = person.getName();
        this.assignment = assignment;
        this.description = description;
        this.isDone = isDone;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     */
    protected Task(Name name, Assignment assignment, Description description, boolean isDone, Set<Tag> tags) {
        CollectionUtil.requireAllNonNull(name, assignment, description, isDone, tags);
        this.name = name;
        this.assignment = assignment;
        this.description = description;
        this.isDone = isDone;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public Description getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns the string representation of the completion status of the task.
     * @return [X] if the task is done. Otherwise, return [ ].
     */
    public String getStatus() {
        return isDone() ? "[X]" : "[ ]";
    }

    /**
     * Returns true if both tasks have the same description, person and assignment.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task other) {
        return other.name.equals(name)
                && other.assignment.equals(assignment)
                && other.description.equals(description);
    }

    /**
     * Creates an edited task based on the provided edits.
     *
     * @throws CommandException when editing a field that does not exist for that task type.
     */
    public abstract Task createEditedCopy(EditTaskDescriptor descriptor) throws CommandException;

    /**
     * Returns true if both tasks have the data and status fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.name.equals(name)
                && otherTask.assignment.equals(assignment)
                && otherTask.description.equals(description)
                && otherTask.isDone == isDone
                && otherTask.getTags().equals(tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, assignment, description, isDone, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("[%s]", isDone()))
                .append(String.format("[%s]", getAssignment()))
                .append(String.format("[%s]", getName()))
                .append("\n")
                .append(getDescription())
                .append("\n")
                .append(getTags().size() == 0 ? "" : getTags());
        return builder.toString();
    }

    /**
     * Compares this task and the input task to decide description alphabetical order.
     * @param other input task.
     * @return 1 if this task's description is alphabetically first, -1 otherwise.
     */
    public int compareByDescriptionAlphabeticalTo(Task other) {
        return this.description.compareByAlphabeticalTo(other.description);
    }
}

