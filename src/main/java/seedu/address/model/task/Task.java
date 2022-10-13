package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Task in the TaskList.
 * Guarantees: description is present and not null, field values are validated, immutable.
 */
public class Task {
    private final Description description;
    private final Deadline deadline;
    private Boolean isDone;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * A constructor that creates an instance of Task.
     * @param description The description of the task.
     */
    public Task(Description description, Deadline deadline, Boolean isDone, Set<Tag> tags) {
        requireAllNonNull(description, deadline, isDone, tags);
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
        this.tags.addAll(tags);
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
    public Boolean getStatus() {
        return isDone;
    }

    /**
     * Returns an icon representing if the task is done.
     * @return String representation of an icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if task contains any of the description or deadline keywords.
     * By default, empty lists will return true.
     *
     * @param descriptionKeywords Possibly empty list containing keywords for {@code Description}.
     * @param deadlineKeywords Possibly empty list containing keywords for {@code Deadline}.
     * @return boolean indicating if task contains supplied keywords.
     */
    public boolean containsKeywordsCaseInsensitive(List<Description> descriptionKeywords,
                                                   List<Deadline> deadlineKeywords) {
        return (descriptionKeywords.isEmpty() || descriptionKeywords.stream().anyMatch(description::equalsIgnoreCase))
                && (deadlineKeywords.isEmpty() || deadlineKeywords.contains(deadline));
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two tasks.
     *
     * @param otherTask Another task.
     * @return boolean indicating whether tasks are the same.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && description.equalsIgnoreCase(otherTask.getDescription());
    }

    /**
     * Returns true if both tasks have the same fields.
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
        return otherTask.getDescription().equals(getDescription())
                && otherTask.getDeadline().equals(getDeadline())
                && otherTask.getStatus().equals(getStatus())
                && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, deadline, isDone, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Status: ")
                .append(getStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
