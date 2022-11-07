package modtrekt.testutil;

import java.time.LocalDate;

import modtrekt.model.module.ModCode;
import modtrekt.model.task.Deadline;
import modtrekt.model.task.Description;
import modtrekt.model.task.Task;

/**
 * A utility class to help with building Deadline objects.
 */
public class DeadlineBuilder {
    public static final String DEFAULT_DESC = "Complete Assignment";
    public static final String DEFAULT_MOD_CODE = "CS2103T";
    public static final LocalDate DEFAULT_DUE_DATE = LocalDate.of(2022, 1, 1);
    public static final boolean DEFAULT_IS_DONE_STATUS = false;
    public static final Task.Priority DEFAULT_PRIORITY = Task.Priority.NONE;
    private Description description;
    private ModCode modCode;
    private LocalDate dueDate;
    private boolean isDone;
    private Task.Priority priority;

    /**
     * Creates a {@code DeadlineBuilder} with the default details.
     */
    public DeadlineBuilder() {
        description = new Description(DEFAULT_DESC);
        dueDate = DEFAULT_DUE_DATE;
        modCode = new ModCode(DEFAULT_MOD_CODE);
        isDone = DEFAULT_IS_DONE_STATUS;
        priority = DEFAULT_PRIORITY;
    }

    /**
     * Sets the {@code Description} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withDescription(String desc) {
        this.description = new Description(desc);
        return this;
    }

    /**
     * Sets the {@code ModCode} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withModCode(String code) {
        this.modCode = new ModCode(code);
        return this;
    }

    /**
     * Sets the {@code dueDate} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * Sets the {@code priority} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withPriority(Task.Priority priority) {
        this.priority = priority;
        return this;
    }

    public Deadline build() {
        return new Deadline(description, modCode, dueDate, isDone, priority);
    }
}
