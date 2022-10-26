package swift.storage;

import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import swift.commons.exceptions.IllegalValueException;
import swift.logic.parser.ParserUtil;
import swift.model.task.Deadline;
import swift.model.task.Description;
import swift.model.task.Task;
import swift.model.task.TaskName;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String id;
    private final String taskName;
    private final String description;
    private final String deadline;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("id") String id, @JsonProperty("taskName") String taskName,
            @JsonProperty("description") String description, @JsonProperty("deadline") String deadline) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        id = source.getId().toString();
        taskName = source.getName().fullName;
        description = source.getDescription().map(Description::toString).orElse(null);
        deadline = source.getDeadline().map(Deadline::toString).orElse(null);
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's
     * {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, UUID.class.getSimpleName()));
        }
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }
        final UUID modelId = UUID.fromString(id);

        if (taskName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskName.class.getSimpleName()));
        }
        if (!TaskName.isValidName(taskName)) {
            throw new IllegalValueException(TaskName.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelName = new TaskName(taskName);

        if (description != null && !Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        Optional<Description> modelDescription = Optional.empty();
        if (description != null) {
            modelDescription = Optional.of(ParserUtil.parseDescription(description));
        }

        if (deadline != null && !Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        Optional<Deadline> modelDeadline = Optional.empty();
        if (deadline != null) {
            modelDeadline = Optional.of(ParserUtil.parseDeadline(deadline));
        }

        return new Task(modelId, modelName, modelDescription, modelDeadline);
    }
}
