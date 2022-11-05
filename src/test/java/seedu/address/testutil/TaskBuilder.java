package seedu.address.testutil;

import seedu.address.model.exam.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskStatus;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TASK_DESCRIPTION = "task description";
    public static final String DEFAULT_MODULE = "CS2103T";

    private Module module;
    private TaskDescription taskDescription;
    private PriorityTag priorityTag;
    private DeadlineTag deadlineTag;
    private TaskStatus taskStatus;
    private Exam linkedExam;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        module = new Module(new ModuleCode(DEFAULT_MODULE));
        taskDescription = new TaskDescription(DEFAULT_TASK_DESCRIPTION);
        priorityTag = null;
        deadlineTag = null;
        taskStatus = TaskStatus.INCOMPLETE;
        linkedExam = null;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        module = taskToCopy.getModule();
        taskDescription = taskToCopy.getDescription();
        priorityTag = taskToCopy.getPriorityTag();
        deadlineTag = taskToCopy.getDeadlineTag();
        taskStatus = taskToCopy.getStatus();
        linkedExam = taskToCopy.getExam();
    }

    /**
     * Sets the {@code TaskDescription} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskDescription(String description) {
        this.taskDescription = new TaskDescription(description);
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code Task} that we are building.
     */
    public TaskBuilder withModule(String module) {
        this.module = new Module(new ModuleCode(module));
        return this;
    }

    /**
     * Sets the {@code TaskStatus} of the {@code Task} that we are building.
     */
    public TaskBuilder withStatus(String complete) {
        this.taskStatus = TaskStatus.of(complete);
        return this;
    }

    /**
     * Sets the {@code Exam} of the {@code Task} that we are building.
     */
    public TaskBuilder withExam(Exam exam) {
        this.linkedExam = exam;
        return this;
    }

    /**
     * Removes the {@code Exam} of the {@code Task} that we are building.
     */
    public TaskBuilder withNoExam() {
        this.linkedExam = null;
        return this;
    }

    /**
     * Sets the {@code PriorityTag} of the {@code Task} which is being built.
     *
     * @param priorityTag The priority tag being set.
     * @return The task builder which now contains the priority tag.
     */
    public TaskBuilder withPriorityTag(PriorityTag priorityTag) {
        this.priorityTag = priorityTag;
        return this;
    }

    /**
     * Sets the {@code DeadlineTag} of the {@code Task} which is being built.
     *
     * @param deadlineTag The deadline tag being set.
     * @return The task builder which now contains the deadline tag.
     */
    public TaskBuilder withDeadlineTag(DeadlineTag deadlineTag) {
        this.deadlineTag = deadlineTag;
        return this;
    }

    public Task build() {
        return new Task(module, taskDescription, taskStatus, priorityTag, deadlineTag, linkedExam);
    }
}
