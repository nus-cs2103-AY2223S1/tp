package modtrekt.model.task;

import modtrekt.model.person.Name;

/**
 * Represents a basic task in the task list.
 * Ensures that necessary details are valid, present and non-null.
 */
public class Task {

    /** String representing description of task */
    public final Name description;

    /**
     * Constructor for an instance of Task.
     *
     * @param description description of task
     */
    public Task(Name description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description.toString();
    }
}
