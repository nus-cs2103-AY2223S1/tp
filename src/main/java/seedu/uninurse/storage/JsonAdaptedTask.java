package seedu.uninurse.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}
 */
public class JsonAdaptedTask {

    private final String taskDescription;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskDescription}.
     */
    @JsonCreator
    public JsonAdaptedTask(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskDescription = source.taskDescription;
    }

    @JsonValue
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (!Task.isValidTaskDescription(taskDescription)) {
            throw new IllegalValueException(Task.MESSAGE_CONSTRAINTS);
        }
        return new Task(taskDescription);
    }
}
