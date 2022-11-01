package taskbook.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import taskbook.commons.exceptions.IllegalValueException;
import taskbook.logic.parser.DateParser;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.model.person.Email;
import taskbook.model.person.Name;
import taskbook.model.tag.Tag;
import taskbook.model.task.Description;
import taskbook.model.task.Event;
import taskbook.model.task.Task;
import taskbook.model.task.enums.Assignment;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedEvent extends JsonAdaptedTask {

    private final String eventDate;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String name, @JsonProperty("assignment") String assignment,
                            @JsonProperty("description") String description, @JsonProperty("isDone") boolean isDone,
                            @JsonProperty("eventDate") String eventDate,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(name, assignment, description, isDone, tagged);
        this.eventDate = eventDate;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        super(source);
        this.eventDate = source.getDate().toString();
    }

    public String getEventDate() {
        return eventDate;
    }

    /**
     * Converts this Jackson-friendly adapted Event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Event toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }

        if (this.getName() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(this.getName())) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(this.getName());

        if (this.getAssignment() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Assignment.class.getSimpleName()));
        }
        if (!Assignment.isValidAssignment(this.getAssignment())) {
            throw new IllegalValueException(Assignment.MESSAGE_CONSTRAINTS);
        }
        final Assignment modelAssignment = Assignment.valueOf(this.getAssignment());

        if (this.getDescription() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Description.isValidDescription(this.getDescription())) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(this.getDescription());

        if (this.getEventDate() == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "Event Date"));
        }
        final LocalDate modelEventDate;
        try {
            modelEventDate = DateParser.parse(getEventDate());
        } catch (ParseException e) {
            throw new IllegalValueException("Date of Event should be in supported date formats.");
        }

        final Set<Tag> modelTags = new HashSet<>(taskTags);
        return new Event(modelName, modelAssignment, modelDescription, this.isDone(), modelEventDate, modelTags);
    }
}
