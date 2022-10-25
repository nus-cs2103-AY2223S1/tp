package modtrekt.model.task;

import java.util.Comparator;

import modtrekt.model.module.ModCode;
import modtrekt.model.util.DeadlineComparator;

/**
 * Represents a basic immutable task in the task list.
 * Ensures that necessary details are valid, present and non-null.
 */
public class Task implements Comparable<Task> {
    public static final Comparator<Task> PRIORITY_COMPARATOR = Comparator.comparingInt(t -> t.getPriority().ordinal());
    public static final Comparator<Task> COMPLETION_COMPARATOR = Comparator.comparing(Task::isDone);
    public static final Comparator<Task> DESCRIPTION_COMPARATOR = Comparator.comparing(Task::getDescription);
    public static final Comparator<Task> DEADLINE_COMPARATOR = new DeadlineComparator();

    /**
     * String representing description of task
     */
    public final ModCode module;
    private final Description description;
    private final Priority priority;
    private final boolean isDone;

    /**
     * Constructor for an instance of Task with a priority.
     *
     * @param description description of task
     * @param module      module code of note's module
     * @param isDone      true if task is done, false otherwise
     * @param priority    priority of task
     */
    public Task(Description description, ModCode module, boolean isDone, Priority priority) {
        this.module = module;
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    public Description getDescription() {
        return this.description;
    }

    public ModCode getModule() {
        return this.module;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public Task setPriority(Priority priority) {
        return new Task(description, module, isDone, priority);
    }

    public Task setAsDone() {
        return new Task(this.description, this.module, true, priority);
    }

    public Task setAsUndone() {
        return new Task(this.description, this.module, false, priority);
    }

    /**
     * Compares task object with another task object and checks if they are the same.
     */
    public boolean isSameTask(Task o) {
        if (this == o) {
            return true;
        }

        return o != null && o.description.equals(this.description) && o.module.equals(this.module)
                && o.isDone() == this.isDone() && o.priority == this.priority;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Task) && isSameTask((Task) other);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", description, module, priority, isDone ? "(done)" : "");
    }

    @Override
    public int compareTo(Task that) {
        return COMPLETION_COMPARATOR.thenComparing(PRIORITY_COMPARATOR.reversed())
                .thenComparing(DEADLINE_COMPARATOR.reversed())
                .thenComparing(DESCRIPTION_COMPARATOR)
                .compare(this, that);
    }

    /**
     * The different priority levels for tasks.
     */
    public enum Priority {
        NONE, LOW, MEDIUM, HIGH
    }
}
