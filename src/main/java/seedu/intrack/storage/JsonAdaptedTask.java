package seedu.intrack.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.intrack.commons.exceptions.IllegalValueException;
import seedu.intrack.model.internship.Task;


/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    private final String taskName;
    private final String taskTime;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName} and {@code taskTime}.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskName") String taskName, @JsonProperty("taskTime") String taskTime) {
        this.taskName = taskName;
        this.taskTime = taskTime;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        this.taskName = source.getTaskName();
        this.taskTime = source.getTaskTime().format(Task.FORMATTER);
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (!Task.isValidTaskName(taskName)) {
            throw new IllegalValueException(Task.MESSAGE_CONSTRAINTS);
        }
        if (!Task.isValidTaskTime(taskTime)) {
            throw new IllegalValueException(Task.TIME_CONSTRAINTS);
        }
        return new Task(taskName, taskTime);
    }
}
