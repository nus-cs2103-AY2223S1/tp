package taskbook.testutil;

import java.time.LocalDate;

import taskbook.model.person.Person;
import taskbook.model.task.Description;
import taskbook.model.task.Event;
import taskbook.model.task.enums.Assignment;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder extends TaskBuilder {

    public static final String DEFAULT_DESCRIPTION = "This is the default Event description.";

    private Person person;
    private Assignment assignment;
    private Description description;
    private boolean isDone;
    private LocalDate eventDate;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        person = new PersonBuilder().build();
        assignment = Assignment.FROM;
        description = new Description(DEFAULT_DESCRIPTION);
        isDone = true;
    }

    /**
     * Sets the {@code Person} of the {@code Event} that we are building.
     */
    public EventBuilder withPerson(Person person) {
        this.person = person;
        return this;
    }

    /**
     * Sets the {@code Assignment} of the {@code Event} that we are building.
     */
    public EventBuilder withAssignment(Assignment assignment) {
        this.assignment = assignment;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Event} that we are building.
     */
    public EventBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code Event} that we are building.
     */
    public EventBuilder withIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * Sets the {@code eventDate} of the {@code Event} that we are building.
     */
    public EventBuilder withEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    /**
     * Builds a {@code Event} based on the parameters stored in the {@code EventBuilder}.
     */
    public Event build() {
        return new Event(this.person, this.assignment, this.description, this.isDone, this.eventDate);
    };
}
