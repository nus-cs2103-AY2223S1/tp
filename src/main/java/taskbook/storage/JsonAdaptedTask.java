package taskbook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import taskbook.commons.exceptions.IllegalValueException;
import taskbook.model.person.Email;
import taskbook.model.person.Name;
import taskbook.model.task.Description;
import taskbook.model.task.Task;
import taskbook.model.task.enums.Assignment;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String name;
    private final String assignment;
    private final String description;
    private final boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("phone") String assignment,
                           @JsonProperty("email") String description, @JsonProperty("address") boolean isDone) {
        this.name = name;
        this.assignment = assignment;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName().fullName;
        assignment = source.getAssignment().name();
        description = source.getDescription().description;
        isDone = source.isDone();
    }

    /**
     * Converts this Jackson-friendly adapted Task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (assignment == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Assignment.class.getSimpleName()));
        }
        if (!Assignment.isValidAssignment(assignment)) {
            throw new IllegalValueException(Assignment.MESSAGE_CONSTRAINTS);
        }
        final Assignment modelAssignment = Assignment.valueOf(assignment);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        return new Task(modelName, modelAssignment, modelDescription, isDone);
    }
}
