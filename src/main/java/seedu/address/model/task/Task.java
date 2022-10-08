package seedu.address.model.task;

import seedu.address.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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
    public Task(Description description, boolean isDone, Set<Tag> tags) {
        requireAllNonNull(description, isDone, tags);
        this.isDone = isDone;
        this.description = description;
        this.tags.addAll(tags);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Description getDescription() { return this.description; }

    public boolean getIsDone() { return this.isDone; }

    /**
     * Returns true if task is done, false if task is not done.
     * @return boolean indicating task completion status.
     */
    public boolean getTaskStatus() {
        return isDone;
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

    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription());
    }
}
