package taskbook.testutil;

import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.task.Description;
import taskbook.model.task.Todo;
import taskbook.model.task.enums.Assignment;

/**
 * A utility class to help with building Todo objects.
 */
public class TodoBuilder {

    public static final String DEFAULT_DESCRIPTION = "This is the default Todo description.";
    public static final String DEFAULT_NAME = "defaultName";

    private Name name;
    private Assignment assignment;
    private Description description;
    private boolean isDone;

    /**
     * Creates a {@code TodoBuilder} with the default details.
     */
    public TodoBuilder() {
        name = new Name(DEFAULT_NAME);
        assignment = Assignment.FROM;
        description = new Description(DEFAULT_DESCRIPTION);
        isDone = true;
    }

    /**
     * Sets the {@code Name} of the {@code Todo} that we are building.
     */
    public TodoBuilder withName(Name name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Todo} that we are building.
     */
    public TodoBuilder withPersonName(Person person) {
        this.name = person.getName();
        return this;
    }

    /**
     * Sets the {@code Assignment} of the {@code Todo} that we are building.
     */
    public TodoBuilder withAssignment(Assignment assignment) {
        this.assignment = assignment;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Todo} that we are building.
     */
    public TodoBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code Todo} that we are building.
     */
    public TodoBuilder withIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * Builds a {@code Todo} based on the parameters stored in the {@code TodoBuilder}.
     */
    public Todo build() {
        return new Todo(this.name, this.assignment, this.description, this.isDone);
    }
}
