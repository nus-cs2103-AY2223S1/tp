package soconnect.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import soconnect.commons.exceptions.IllegalValueException;
import soconnect.model.tag.Tag;
import soconnect.model.todo.Date;
import soconnect.model.todo.Description;
import soconnect.model.todo.Priority;
import soconnect.model.todo.Todo;

/**
 * Jackson-friendly version of {@link Todo}.
 */
class JsonAdaptedTodo {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Todo's %s field is missing!";

    private final String description;
    private final String date;
    private final String priority;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTodo} with the given details.
     */
    @JsonCreator
    public JsonAdaptedTodo(@JsonProperty("description") String description,@JsonProperty("date") String date,
                           @JsonProperty("priority") String priority,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.description = description;
        this.date = date;
        this.priority = priority;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Todo} into this class for Jackson use.
     */
    public JsonAdaptedTodo(Todo source) {
        description = source.getDescription().value;
        date = source.getDate().date.format(Date.DATE_FORMATTER);
        priority = source.getPriority().priority;
        tagged.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted todo object into the model's {@code Todo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted todo.
     */
    public Todo toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        isDescriptionValid();
        isDateValid();
        isPriorityValid();
        final Description modelDescription = new Description(description);
        final Date modelDate = new Date(date);
        final Priority modelPriority = new Priority(priority);
        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Todo(modelDescription,modelDate, modelPriority, modelTags);
    }

    private void isDescriptionValid() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
    }

    private void isDateValid() throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }

        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINS);
        }
    }

    private void isPriorityValid() throws IllegalValueException {
        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Priority.class.getSimpleName()));
        }
        if (!Priority.isValidPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
    }

}
