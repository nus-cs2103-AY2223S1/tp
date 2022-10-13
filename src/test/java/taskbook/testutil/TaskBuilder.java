package taskbook.testutil;

import taskbook.model.person.Person;
import taskbook.model.task.Description;
import taskbook.model.task.Task;
import taskbook.model.task.Todo;
import taskbook.model.task.enums.Assignment;

/**
 * A utility class to help with building Person objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_DESCRIPTION = "This is the default task description.";

    private Person person;
    private Assignment assignment;
    private Description description;
    private boolean isDone;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        person = new PersonBuilder().build();
        assignment = Assignment.FROM;
        description = new Description(DEFAULT_DESCRIPTION);
        isDone = true;
    }

    /**
     * Sets the {@code Person} of the {@code Task} that we are building.
     */
    public TaskBuilder withPerson(Person person) {
        this.person = person;
        return this;
    }

    /**
     * Sets the {@code Assignment} of the {@code Task} that we are building.
     */
    public TaskBuilder withAssignment(Assignment assignment) {
        this.assignment = assignment;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Task} that we are building.
     */
    public TaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code Task} that we are building.
     */
    public TaskBuilder withIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * Builds a {@code Task} based on the parameters stored in the {@code TaskBuilder}.
     *
     * @return Task, built from the parameters provided.
     */
    public Task build() {
        return new Todo(person, assignment, description, isDone);
    }
}
