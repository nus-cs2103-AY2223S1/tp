package jarvis.storage.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jarvis.commons.exceptions.IllegalValueException;
import jarvis.model.task.Deadline;
import jarvis.model.task.Task;
import jarvis.model.task.TaskDesc;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tasks's %s field is missing!";

    private final String taskDesc;
    private final String deadline;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskDesc") String taskDesc, @JsonProperty("deadline") String deadline) {
        this.taskDesc = taskDesc;
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskDesc = source.getDesc().taskDesc;
        deadline = source.getDeadline().deadline;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Task toModelType() throws IllegalValueException {

        if (taskDesc == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDesc.class.getSimpleName()));
        }
        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName()));
        }
        final TaskDesc modelTaskDesc = new TaskDesc(taskDesc);
        final Deadline modelDeadline = new Deadline(deadline);
        return new Task(modelTaskDesc, modelDeadline);
    }

}
