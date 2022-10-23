package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.exam.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.tag.exceptions.DeadlineTagAlreadyExistsException;
import seedu.address.model.tag.exceptions.PriorityTagAlreadyExistsException;

/**
 * Task class represents a task which stores the module code and the
 * description of the task.
 */
public class Task {
    private final Module module;
    private final TaskDescription description;
    private final PriorityTag priorityTag;
    private final DeadlineTag deadlineTag;
    private final TaskStatus status;
    private final Exam linkedExam;

    /**
     * The constructor of the Task class. Sets the module and
     * description of the task.
     *
     * @param module The module being added.
     * @param description The description of the task.
     */
    public Task(Module module, TaskDescription description) {
        this.module = module;
        this.description = description;
        this.status = TaskStatus.INCOMPLETE;
        priorityTag = null;
        deadlineTag = null;
        linkedExam = null;
    }

    /**
     * The constructor of the Task class. Sets the module and
     * description of the task.
     *
     * @param module The module being added.
     * @param description The description of the task.
     */
    public Task(Module module, TaskDescription description, TaskStatus status) {
        this.module = module;
        this.description = description;
        this.status = status;
        priorityTag = null;
        deadlineTag = null;
        linkedExam = null;
    }

    /**
     * The constructor of the Task class. Sets the module, description,
     * completion status, the priority status of the task and the deadline
     * of the task.
     *
     * @param module The module being set.
     * @param description The description being set.
     * @param status The completion status of the task.
     * @param priorityTag The tag marking the priority status of the task.
     * @param deadlineTag The tag marking the deadline of the task.
     */
    public Task(Module module, TaskDescription description, TaskStatus status, PriorityTag priorityTag,
            DeadlineTag deadlineTag) {
        this.module = module;
        this.description = description;
        this.status = status;
        this.priorityTag = priorityTag;
        this.deadlineTag = deadlineTag;
        linkedExam = null;
    }

    /**
     * The constructor of the Task class. Sets the module, description,
     * completion status, the priority status of the task, the deadline
     * of the task and the exam description of the task.
     *
     * @param module The module being set.
     * @param description The description being set.
     * @param status The completion status of the task.
     * @param priorityTag The tag marking the priority status of the task.
     * @param deadlineTag The tag marking the deadline of the task.
     * @param linkedExam The exam the task is linked to.
     */
    public Task(Module module, TaskDescription description, TaskStatus status, PriorityTag priorityTag,
                DeadlineTag deadlineTag, Exam linkedExam) {
        this.module = module;
        this.description = description;
        this.status = status;
        this.priorityTag = priorityTag;
        this.deadlineTag = deadlineTag;
        this.linkedExam = linkedExam;
    }

    public TaskDescription getDescription() {
        return description;
    }

    public Module getModule() {
        return module;
    }

    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Returns true if both tasks have the same data fields.
     */
    public boolean isSameTask(Task otherTask) {
        return this.equals(otherTask);
    }

    /**
     * Returns true if task is complete.
     */
    public boolean isComplete() {
        return this.status.isComplete();
    }

    /**
     * Marks the task as complete
     * and returns the task.
     */
    public Task mark() {
        return new Task(module, description, TaskStatus.COMPLETE, priorityTag, deadlineTag, linkedExam);
    }

    public Task setPriorityTag(PriorityTag tag) {
        requireNonNull(tag);
        if (priorityTag != null) {
            throw new PriorityTagAlreadyExistsException();
        }
        return new Task(module, description, status, tag, deadlineTag, linkedExam);
    }

    public boolean hasPriorityTag() {
        return priorityTag != null;
    }

    public PriorityTag getPriorityTag() {
        return priorityTag;
    }

    public boolean hasDeadlineTag() {
        return deadlineTag != null;
    }

    public DeadlineTag getDeadlineTag() {
        return deadlineTag;
    }

    public Task setDeadlineTag(DeadlineTag tag) {
        requireNonNull(tag);
        if (deadlineTag != null) {
            throw new DeadlineTagAlreadyExistsException();
        }
        return new Task(module, description, status, priorityTag, tag, linkedExam);
    }

    public boolean isLinked() {
        return linkedExam != null;
    }

    public Exam getExam() {
        return linkedExam;
    }

    /**
     * Unmarks (labels as incomplete) the task
     * and returns the task.
     */
    public Task unmark() {
        return new Task(module, description, TaskStatus.INCOMPLETE, priorityTag, deadlineTag, linkedExam);
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code this}
     * edited with {@code editTaskDescriptor}.
     */
    public Task edit(EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(editTaskDescriptor);

        Module updatedModule = editTaskDescriptor.getModule().orElse(module);
        TaskDescription updatedDescription = editTaskDescriptor.getDescription().orElse(description);

        if (editTaskDescriptor.getModule().isPresent()
            && !editTaskDescriptor.getModule().get().equals(module)) {
            return new Task(updatedModule, updatedDescription, status, priorityTag, deadlineTag);
        }

        return new Task(updatedModule, updatedDescription, status, priorityTag, deadlineTag, linkedExam);
    }

    /**
     * Links the task to the exam in the exam list.
     *
     * @param exam The exam which the task will be linked to.
     * @return Task object which now contains the linked exam.
     */
    public Task linkTask(Exam exam) {
        requireNonNull(exam);
        return new Task(module, description, status, priorityTag, deadlineTag, exam);
    }

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
                && otherTask.getModule().equals(getModule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, module);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModule())
                .append("; Description: ")
                .append(getDescription());
        return builder.toString();
    }
}
