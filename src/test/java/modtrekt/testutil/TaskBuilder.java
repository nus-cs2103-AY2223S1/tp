package modtrekt.testutil;


import modtrekt.model.task.Description;
import modtrekt.model.task.Task;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_DESC = "Complete Assignment";

    private Description description;
    private boolean isArchived;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        description = new Description(DEFAULT_DESC);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        description = taskToCopy.getDescription();
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String desc) {
        this.description = new Description(desc);
        return this;
    }

    /**
     * Sets the {@code isArchived} of the {@code Task} that we are building.
     */
    public TaskBuilder withIsArchived(boolean isArchived) {
        this.isArchived = isArchived;
        return this;
    }

    public Task build() {
        return new Task(description, isArchived);
    }
}
