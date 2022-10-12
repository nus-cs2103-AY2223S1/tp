package swift.model.task;

import static java.util.Objects.requireNonNull;

import swift.commons.core.index.Index;

/**
 * Represents a Task in the address book.
 */
public class Task {
    public final TaskName taskName;
    public final Index contactIndex;

    /**
     * Constructs a {@code Task}.
     *
     * @param taskName     A valid task name.
     * @param contactIndex A valid contact index.
     */
    public Task(TaskName taskName, Index contactIndex) {
        requireNonNull(taskName);
        requireNonNull(contactIndex);
        this.taskName = taskName;
        this.contactIndex = contactIndex;
    }

    public TaskName getTaskName() {
        return taskName;
    }

    public Index getContactIndex() {
        return contactIndex;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Task
                && taskName.equals(((Task) other).taskName)
                && contactIndex.equals(((Task) other).contactIndex));
    }

    @Override
    public int hashCode() {
        return taskName.hashCode() ^ contactIndex.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + taskName.toString() + ']';
    }
}
