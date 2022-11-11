package jarvis.storage;

import static jarvis.commons.util.JsonUtil.checkNullArgument;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jarvis.model.Task;
import jarvis.model.TaskDeadline;
import jarvis.model.TaskDesc;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String taskDesc;
    private final LocalDate deadline;
    private final boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskDesc") String taskDesc,
                           @JsonProperty("deadline") LocalDate deadline,
                           @JsonProperty("isDone") boolean isDone) {
        this.taskDesc = taskDesc;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskDesc = source.getDesc().taskDesc;
        deadline = source.getDeadline();
        isDone = source.isDone();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Task} object.
     *
     * @throws IllegalArgumentException if there were any data constraints violated in the adapted person.
     */
    public Task toModelType() throws IllegalArgumentException {
        checkNullArgument(TaskDesc.class, MISSING_FIELD_MESSAGE_FORMAT, taskDesc);
        final TaskDesc modelTaskDesc = new TaskDesc(taskDesc);

        final TaskDeadline modelTaskDeadline = deadline == null ? null : new TaskDeadline(deadline);

        Task task = new Task(modelTaskDesc, modelTaskDeadline);

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

}
