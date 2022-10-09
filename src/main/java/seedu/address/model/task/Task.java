package seedu.address.model.task;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Task in the TaskList.
 */
public class Task {
    private Description description;
    private boolean isDone;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * A constructor that creates an instance of Task.
     * @param description The description of the task.
     */
    public Task(Description description) {
        this.isDone = false;
        this.description = description;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if task is done, false if task is not done.
     * @return boolean indicating task completion status.
     */
    public boolean getTaskStatus() {
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
     * Marks task as done.
     */
    public void markTask() {
        isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void unmarkTask() {
        isDone = false;
    }

    /**
     * Returns true if both tasks have the same description.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription());
    }

}
