package modtrekt.model.task;

import java.util.Comparator;

import modtrekt.model.module.ModCode;

/**
 * Represents a basic immutable task in the task list.
 * Ensures that necessary details are valid, present and non-null.
 */
public class Task implements Comparable<Task> {
    public static final Comparator<Task> PRIORITY_COMPARATOR = Comparator.comparingInt(t -> t.getPriority().ordinal());
    public static final Comparator<Task> ARCHIVAL_COMPARATOR = Comparator.comparing(Task::isArchived);
    public static final Comparator<Task> DESCRIPTION_COMPARATOR = Comparator.comparing(Task::getDescription);

    /**
     * String representing description of task
     */
    public final ModCode module;
    private final Description description;
    private final Priority priority;
    private final boolean isArchived;

    /**
     * Constructor for an instance of Task with a priority.
     *
     * @param description description of task
     * @param module      module code of note's module
     * @param isArchived  true if task is completed/archived
     * @param priority    priority of task
     */
    public Task(Description description, ModCode module, boolean isArchived, Priority priority) {
        this.module = module;
        this.description = description;
        this.isArchived = isArchived;
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

    public boolean isArchived() {
        return this.isArchived;
    }

    public Task setPriority(Priority priority) {
        return new Task(description, module, isArchived, priority);
    }

    public Task archive() {
        return new Task(this.description, this.module, true, priority);
    }

    public Task unarchive() {
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
                && o.isArchived() == this.isArchived() && o.priority == this.priority;
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
        return String.format("%s %s %s %s", description, module, priority, isArchived ? "(ARCHIVED)" : "");
    }

    @Override
    public int compareTo(Task that) {
        return ARCHIVAL_COMPARATOR.thenComparing(PRIORITY_COMPARATOR.reversed())
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
