package seedu.uninurse.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.model.task.DateTime;
import seedu.uninurse.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}
 */
public class JsonAdaptedTask {

    private final String taskDescription;
    private final JsonAdaptedDateTime dateTime;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskDescription}.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskDescription") String taskDescription,
                           @JsonProperty("dateTime") JsonAdaptedDateTime dateTime) {
        this.taskDescription = taskDescription;
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskDescription = source.taskDescription;
        dateTime = new JsonAdaptedDateTime(source.dateTime);
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (!Task.isValidTaskDescription(taskDescription) || !DateTime.isValidDateTime(dateTime.getDateTime())) {
            throw new IllegalValueException(Task.MESSAGE_CONSTRAINTS);
        }
        return new Task(taskDescription, dateTime.toModelType());
    }
}
