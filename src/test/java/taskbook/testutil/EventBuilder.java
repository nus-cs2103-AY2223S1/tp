package taskbook.testutil;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import taskbook.logic.parser.ParserUtil;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.tag.Tag;
import taskbook.model.task.Description;
import taskbook.model.task.Event;
import taskbook.model.task.enums.Assignment;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_DESCRIPTION = "This is the default Event description.";
    public static final String DEFAULT_NAME = "defaultName";

    private Name name;
    private Assignment assignment;
    private Description description;
    private boolean isDone;
    private LocalDate eventDate;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        assignment = Assignment.FROM;
        description = new Description(DEFAULT_DESCRIPTION);
        isDone = false;
        eventDate = LocalDate.parse("2007-07-07");
    }

    /**
     * Sets the {@code Person} of the {@code Event} that we are building.
     */
    public EventBuilder withPersonName(Person person) {
        this.name = person.getName();
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
     * Sets the {@code tags} of the {@code Event} that we are building.
     */
    public EventBuilder withTags(String... tags) {
        try {
            this.tags.addAll(ParserUtil.parseTags(Arrays.asList(tags)));
        } catch (ParseException e) {
            return this;
        }
        return this;
    }

    /**
     * Builds a {@code Event} based on the parameters stored in the {@code EventBuilder}.
     */
    public Event build() {
        return new Event(this.name, this.assignment, this.description, this.isDone, this.eventDate, this.tags);
    }
}
