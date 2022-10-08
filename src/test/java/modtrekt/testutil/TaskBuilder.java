package modtrekt.testutil;


import modtrekt.model.task.Description;
import modtrekt.model.task.Task;

/**
 * A utility class to help with building Person objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_DESC = "Amy Bee";

    private Description description;

    /**
     * Creates a {@code PersonBuilder} with the default details.
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
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public TaskBuilder withDescription(String desc) {
        this.description = new Description(desc);
        return this;
    }


    public Task build() {
        return new Task(description);
    }

}
