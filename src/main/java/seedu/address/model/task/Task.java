package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Task in the TaskList.
 */
public class Task {
    private Description description;
    private Deadline deadline;
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
        // TODO: Edit after implementing Deadline class
        this.deadline = new Deadline("");
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns description of task.
     * @return Description Details of task.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Returns deadline of task.
     * @return deadline Deadline of task.
     */
    public Deadline getDeadline() {
        return deadline;
    }

    /**
     * Returns true if task is done, false if task is not done.
     * @return boolean indicating task completion status.
     */
    public boolean getTaskStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
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
     * Returns true if both tasks have matching descriptions, false otherwise.
     * @param otherTask Another task.
     * @return boolean indicating whether tasks are the same.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getDescription().equals(getDescription());
    }

    /**
     * Returns true if task contains any of the description or deadline keywords.
     * By default, empty lists will return true.
     *
     * @param descriptionKeywords Possibly empty list containing keywords for {@code Description}.
     * @param deadlineKeywords Possibly empty list containing keywords for {@code Deadline}.
     * @return boolean indicating if task contains supplied keywords.
     */
    public boolean containsKeywords(List<Description> descriptionKeywords, List<Deadline> deadlineKeywords) {
        return (descriptionKeywords.isEmpty() || descriptionKeywords.contains(description))
                && (deadlineKeywords.isEmpty() || deadlineKeywords.contains(deadline));
    }
}
