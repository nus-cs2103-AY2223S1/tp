package taskbook.testutil;

import java.time.LocalDate;

import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.task.Deadline;
import taskbook.model.task.Description;
import taskbook.model.task.enums.Assignment;

/**
 * A utility class to help with building Deadline objects.
 */
public class DeadlineBuilder {

    public static final String DEFAULT_DESCRIPTION = "This is the default Deadline description.";
    public static final String DEFAULT_NAME = "defaultName";

    private Name name;
    private Assignment assignment;
    private Description description;
    private boolean isDone;
    private LocalDate deadlineDate;

    /**
     * Creates a {@code DeadlineBuilder} with the default details.
     */
    public DeadlineBuilder() {
        name = new Name(DEFAULT_NAME);
        assignment = Assignment.FROM;
        description = new Description(DEFAULT_DESCRIPTION);
        isDone = true;
    }

    /**
     * Sets the {@code Name} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withName(Name name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withPersonName(Person person) {
        this.name = person.getName();
        return this;
    }

    /**
     * Sets the {@code Assignment} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withAssignment(Assignment assignment) {
        this.assignment = assignment;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withDescription(String description) {
        this.description = new Description(description);
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
     * Sets the {@code deadlineDate} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
        return this;
    }

    /**
     * Builds a {@code Deadline} based on the parameters stored in the {@code DeadlineBuilder}.
     */
    public Deadline build() {
        return new Deadline(this.name, this.assignment, this.description, this.isDone, this.deadlineDate);
    }
}
