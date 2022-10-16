package taskbook.testutil;

import taskbook.model.person.Person;
import taskbook.model.task.Description;
import taskbook.model.task.Todo;
import taskbook.model.task.enums.Assignment;

/**
 * A utility class to help with building Todo objects.
 */
public class TodoBuilder {

    public static final String DEFAULT_DESCRIPTION = "This is the default Todo description.";

    private Person person;
    private Assignment assignment;
    private Description description;
    private boolean isDone;

    /**
     * Creates a {@code TodoBuilder} with the default details.
     */
    public TodoBuilder() {
        person = new PersonBuilder().build();
        assignment = Assignment.FROM;
        description = new Description(DEFAULT_DESCRIPTION);
        isDone = true;
    }

    /**
     * Sets the {@code Person} of the {@code Todo} that we are building.
     */
    public TodoBuilder withPerson(Person person) {
        this.person = person;
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
        return new Todo(this.person, this.assignment, this.description, this.isDone);
    };
}
