package seedu.uninurse.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.model.task.DateTime;
import seedu.uninurse.model.task.RecurringTasks;
import seedu.uninurse.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {

    private final String taskDescription;
    private final JsonAdaptedDateTime dateTime;
    private final RecurringTasks.Frequency freq;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskDescription}.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskDescription") String taskDescription,
                           @JsonProperty("dateTime") JsonAdaptedDateTime dateTime,
                           @JsonProperty("frequency") RecurringTasks.Frequency freq) {
        this.taskDescription = taskDescription;
        this.dateTime = dateTime;
        this.freq = freq;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskDescription = source.taskDescription;
        dateTime = new JsonAdaptedDateTime(source.dateTime);
        if (source instanceof RecurringTasks) {
            RecurringTasks recurTask = (RecurringTasks) source;
            freq = recurTask.recurrence;
        } else {
            freq = null;
        }
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

        if (freq == null) {
            return new Task(taskDescription, dateTime.toModelType());
        } else {
            return new RecurringTasks(taskDescription, dateTime.toModelType(), freq);
        }
    }
}
