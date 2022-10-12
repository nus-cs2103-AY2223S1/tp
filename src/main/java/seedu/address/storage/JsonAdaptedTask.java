package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String title;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("title") String title, @JsonProperty("description") String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        title = source.getTitle().title;
        description = source.getDescription().description;
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskTitle.class.getSimpleName()));
        }
        if (!TaskTitle.isValidTitle(title)) {
            throw new IllegalValueException(TaskTitle.MESSAGE_CONSTRAINTS);
        }
        final TaskTitle modelTitle = new TaskTitle(title);

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDescription.class.getSimpleName()));
        }
        if (!TaskDescription.isValidDescription(description)) {
            throw new IllegalValueException(TaskDescription.MESSAGE_CONSTRAINTS);
        }
        final TaskDescription modelDescription = new TaskDescription(description);

        return new Task(modelTitle, modelDescription);
    }
}
