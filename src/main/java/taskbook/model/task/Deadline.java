package taskbook.model.task;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.tag.Tag;
import taskbook.model.task.enums.Assignment;

/**
 * Represents a Deadline in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Deadline extends Task {

    private final LocalDate date;

    /**
     * Every field must be present and not null.
     */
    public Deadline(Person person, Assignment assignment, Description description, boolean isDone, LocalDate date) {
        super(person, assignment, description, isDone);
        this.date = date;
    }

    /**
     * Every field must be present and not null.
     */
    public Deadline(Name name, Assignment assignment, Description description, boolean isDone, LocalDate date) {
        super(name, assignment, description, isDone);
        this.date = date;
    }

    /**
     * Every field must be present and not null.
     */
    public Deadline(Person person, Assignment assignment, Description description, boolean isDone,
                    LocalDate date, Set<Tag> tags) {
        super(person, assignment, description, isDone, tags);
        this.date = date;
    }

    /**
     * Every field must be present and not null.
     */
    public Deadline(Name name, Assignment assignment, Description description, boolean isDone,
                    LocalDate date, Set<Tag> tags) {
        super(name, assignment, description, isDone, tags);
        this.date = date;
    }

    @Override
    public String getStatus() {
        return isDone() ? "[X]  [D]" : "[  ]  [D]";
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean hasDate() {
        return true;
    }

    @Override
    public boolean isSameTask(Task other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherTask = (Deadline) other;
        return super.isSameTask(other) && date.equals(otherTask.date);
    }

    @Override
    public Deadline createEditedCopy(EditTaskDescriptor descriptor) {
        Name name = descriptor.getName().orElse(getName());
        Assignment assignment = descriptor.getAssignment().orElse(getAssignment());
        Description description = descriptor.getDescription().orElse(getDescription());
        Boolean isDone = descriptor.getIsDone().orElse(isDone());
        LocalDate date = descriptor.getDate().orElse(getDate());
        Set<Tag> tags = descriptor.getTags().orElse(getTags());

        return new Deadline(name, assignment, description, isDone, date, tags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherTask = (Deadline) other;
        return super.equals(other) && date.equals(otherTask.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date);
    }

    @Override
    public String toUiString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
               .append(String.format(" [by %s]", getDate().toString()));;
        return builder.toString();
    }

    @Override
    public String toString() {
        String taskString = super.toString();
        int newLineIndex = taskString.indexOf("\n");
        return taskString.substring(0, newLineIndex)
                + String.format("[%s]", getDate().toString())
                + taskString.substring(newLineIndex);
    }
}
