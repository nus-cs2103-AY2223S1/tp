package taskbook.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import taskbook.commons.exceptions.IllegalValueException;
import taskbook.model.person.Email;
import taskbook.model.person.Name;
import taskbook.model.tag.Tag;
import taskbook.model.task.Description;
import taskbook.model.task.Task;
import taskbook.model.task.Todo;
import taskbook.model.task.enums.Assignment;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTodo extends JsonAdaptedTask {

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTodo(@JsonProperty("name") String name, @JsonProperty("assignment") String assignment,
                           @JsonProperty("description") String description, @JsonProperty("isDone") boolean isDone,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(name, assignment, description, isDone, tagged);
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTodo(Task source) {
        super(source);
    }

    /**
     * Converts this Jackson-friendly adapted Todo object into the model's {@code Todo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Todo toModelType() throws IllegalValueException {
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

        final Set<Tag> modelTags = new HashSet<>(taskTags);
        return new Todo(modelName, modelAssignment, modelDescription, this.isDone(), modelTags);
    }
}
