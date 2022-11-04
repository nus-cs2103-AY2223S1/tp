package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Represents a Task in the TaskList.
 * Guarantees: description is present and not null, field values are validated, immutable.
 */
public class Task {
    /** {@code Predicate} that returns true for archived tasks, false otherwise */
    public static final Predicate<Task> PREDICATE_SHOW_ARCHIVED_TASKS = Task::getArchivalStatus;

    /** {@code Predicate} that returns true for non-archived tasks, false otherwise */
    public static final Predicate<Task> PREDICATE_SHOW_NON_ARCHIVED_TASKS = task -> !task.getArchivalStatus();

    public static final String MESSAGE_TASK_COMPLETED = "Completed";
    public static final String MESSAGE_TASK_NOT_COMPLETED = "Not Completed";

    public static final String INPUT_TASK_COMPLETED = "complete";
    public static final String INPUT_TASK_NOT_COMPLETED = "incomplete";

    public static final String COMPLETION_STATUS_MESSAGE_CONSTRAINTS =
            "Completion status should be complete or incomplete";

    private final Description description;
    private final Deadline deadline;
    private final Boolean isDone;
    private final Boolean isArchived;

    private final Set<Tag> tags = new HashSet<>();
    private final Id id;

    /**
     * A constructor that creates an instance of Task with a new id.
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     * @param isDone The status of the task.
     * @param tags The set of tags of the task.
     */
    public Task(Description description, Deadline deadline, Boolean isDone, Set<Tag> tags) {
        requireAllNonNull(description, deadline, isDone, tags);
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
        this.isArchived = false;
        this.tags.addAll(tags);
        id = new Id();
    }

    /**
     * A constructor that creates an instance of Task with an existing id.
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     * @param isDone The completion status of the task.
     * @param isArchived The archival status of the task.
     * @param tags The set of tags of the task.
     * @param id The id of the task.
     */
    public Task(Description description, Deadline deadline, Boolean isDone, Boolean isArchived, Set<Tag> tags, Id id) {
        requireAllNonNull(description, deadline, isDone, tags);
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
        this.isArchived = isArchived;
        this.tags.addAll(tags);
        this.id = id;
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
    public Boolean getCompletionStatus() {
        return isDone;
    }

    /**
     * Returns true if task is archived, false if task is not.
     * @return boolean indicating task archival status.
     */
    public Boolean getArchivalStatus() {
        return isArchived;
    }

    /**
     * Returns a string representing if the task is done.
     * @return String representation of a task completion.
     */
    public String getCompletionStatusForDisplay() {
        return (isDone ? MESSAGE_TASK_COMPLETED : MESSAGE_TASK_NOT_COMPLETED);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns the id of the task.
     * @return id Id of the task.
     */
    public Id getId() {
        return id;
    }

    /**
     * Returns true if task contains any of the description or deadline keywords.
     * By default, empty lists will return true.
     *
     * @param descriptionKeywords Possibly empty list containing keywords for {@code Description}.
     * @param deadlineKeywords Possibly empty list containing keywords for {@code Deadline}.
     * @param tags Possibly empty set containing search terms for {@code Tag}.
     * @return boolean indicating if task contains supplied keywords.
     */
    public boolean containsKeywordsCaseInsensitive(List<Description> descriptionKeywords,
                                                   List<Deadline> deadlineKeywords,
                                                   List<Boolean> completionStatusKeywords,
                                                   Set<Tag> tags) {
        return (descriptionKeywords.isEmpty() || descriptionKeywords.stream().anyMatch(description::equalsIgnoreCase))
                && (deadlineKeywords.isEmpty() || deadlineKeywords.contains(deadline))
                && (completionStatusKeywords.isEmpty() || completionStatusKeywords.contains(isDone))
                && (tags.isEmpty() || !Collections.disjoint(tags, this.tags));
    }

    /**
     * Returns true if the task deadline is earlier than or on the day of the given deadline.
     *
     * @param deadline The deadline to check against.
     * @return boolean indicating if task deadline is before or on the day of the given deadline.
     */
    public boolean deadlinesUpToAndIncluding(Deadline deadline) {
        requireNonNull(deadline);
        return this.deadline.compareTo(deadline) <= 0;
    }

    public boolean containTag(Tag tag) {
        return tags.contains(tag);
    }

    public int getTagSize() {
        return tags.size();
    }

    /**
     * Returns true if both tasks have the same description, deadline and tags.
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
                && description.equalsIgnoreCase(otherTask.getDescription())
                && deadline.equals(otherTask.getDeadline())
                && tags.equals(otherTask.getTags());
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
                && otherTask.getCompletionStatus().equals(getCompletionStatus())
                && otherTask.getArchivalStatus().equals(getArchivalStatus())

                && otherTask.getTags().equals(getTags())
                && otherTask.getId().equals(getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, deadline, isDone, isArchived, tags, id);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append("; Deadline: ")
                .append(getDeadline())
                .append("; Completion Status: ")
                .append(getCompletionStatusForDisplay());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        if (getArchivalStatus()) {
            builder.append(";\nTask is Archived");
        }

        return builder.toString();
    }
}
