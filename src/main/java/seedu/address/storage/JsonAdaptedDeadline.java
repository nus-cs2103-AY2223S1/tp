package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.FormatDate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskTitle;

/**
 * JsonAdaptedDeadline handles the cases of loading and writing for Deadlines.
 */
public class JsonAdaptedDeadline extends JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Deadline's %s field is missing!";
    private final String title;
    private final String description;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedDeadline(@JsonProperty("title") String title, @JsonProperty("description") String description,
                               @JsonProperty("date") String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedDeadline(Task source) {
        Deadline deadline = (Deadline) source;
        title = deadline.getTitle().title;
        description = deadline.getDescription().description;
        date = FormatDate.correctDateFormat(deadline.getDate().toString());
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

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    FormatDate.class.getSimpleName()));
        }
        if (!FormatDate.isValidDate(date)) {
            throw new IllegalValueException(FormatDate.MESSAGE_CONSTRAINTS);
        }
        final FormatDate modelDate = new FormatDate(date);

        return new Deadline(modelTitle, modelDescription, modelDate);
    }
}
