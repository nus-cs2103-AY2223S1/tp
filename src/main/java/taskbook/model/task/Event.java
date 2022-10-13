package taskbook.model.task;

import java.time.LocalDate;
import java.util.Objects;

import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.task.enums.Assignment;

/**
 * Represents an Event in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event extends Task {

    private final LocalDate date;

    /**
     * Every field must be present and not null.
     */
    public Event(Person person, Assignment assignment, Description description, boolean isDone, LocalDate date) {
        super(person, assignment, description, isDone);
        this.date = date;
    }

    /**
     * Every field must be present and not null.
     */
    public Event(Name name, Assignment assignment, Description description, boolean isDone, LocalDate date) {
        super(name, assignment, description, isDone);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean isSameTask(Task other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        // TODO: Include isDone in equality check?
        Event otherTask = (Event) other;
        return otherTask.getName().equals(getName())
                && otherTask.getAssignment().equals(getAssignment())
                && otherTask.getDescription().equals(getDescription());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        // TODO: Include isDone in equality check?
        Event otherTask = (Event) other;
        return otherTask.getName().equals(getName())
                && otherTask.getAssignment().equals(getAssignment())
                && otherTask.getDescription().equals(getDescription())
                && (otherTask.isDone() == (isDone()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date);
    }

}
