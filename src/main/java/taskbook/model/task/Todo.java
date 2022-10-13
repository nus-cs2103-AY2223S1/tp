package taskbook.model.task;

import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.task.enums.Assignment;

/**
 * Represents a To-do in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Todo extends Task {

    /**
     * Every field must be present and not null.
     */
    public Todo(Person person, Assignment assignment, Description description, boolean isDone) {
        super(person, assignment, description, isDone);
    }

    /**
     * Every field must be present and not null.
     */
    public Todo(Name name, Assignment assignment, Description description, boolean isDone) {
        super(name, assignment, description, isDone);
    }

    @Override
    public boolean isSameTask(Task other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Todo)) {
            return false;
        }

        Todo otherTask = (Todo) other;
        return otherTask.getName().equals(getName())
                && otherTask.getAssignment().equals(getAssignment())
                && otherTask.getDescription().equals(getDescription());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Todo)) {
            return false;
        }

        Todo otherTask = (Todo) other;
        return otherTask.getName().equals(getName())
                && otherTask.getAssignment().equals(getAssignment())
                && otherTask.getDescription().equals(getDescription())
                && (otherTask.isDone() == (isDone()));
    }
}


