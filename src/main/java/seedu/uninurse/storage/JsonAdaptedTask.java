package seedu.uninurse.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.model.task.DateTime;
import seedu.uninurse.model.task.NonRecurringTask;
import seedu.uninurse.model.task.Recurrence;
import seedu.uninurse.model.task.RecurringTask;
import seedu.uninurse.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {

    private final String taskDescription;
    private final JsonAdaptedDateTime dateTime;
    private final Recurrence recurrence;
    private final int frequency;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskDescription}.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskDescription") String taskDescription,
                           @JsonProperty("dateTime") JsonAdaptedDateTime dateTime,
                           @JsonProperty("recurrence") Recurrence recur,
                           @JsonProperty("frequency") int frequency) {
        this.taskDescription = taskDescription;
        this.dateTime = dateTime;
        this.recurrence = recur;
        this.frequency = frequency;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskDescription = source.getTaskDescription();
        dateTime = new JsonAdaptedDateTime(source.getDateTime());
        if (source instanceof RecurringTask) {
            RecurringTask recurTask = (RecurringTask) source;
            recurrence = recurTask.getRecurrence();
            frequency = recurTask.getFrequency();
        } else {
            recurrence = null;
            frequency = 0;
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

        if (recurrence == null) {
            return new NonRecurringTask(taskDescription, dateTime.toModelType());
        } else {
            return new RecurringTask(taskDescription, dateTime.toModelType(), recurrence, frequency);
        }
    }
}
