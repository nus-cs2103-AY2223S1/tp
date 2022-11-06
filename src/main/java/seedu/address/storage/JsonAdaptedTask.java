package seedu.address.storage;

import static seedu.address.commons.util.DateUtil.getLocalDate;
import static seedu.address.commons.util.DateUtil.getLocalDateString;
import static seedu.address.commons.util.DateUtil.isLocalDateString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}
 */
public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing !";
    private final String title;
    private final String deadline;
    private final boolean status;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("title") String title, @JsonProperty("deadline") String deadline,
                           @JsonProperty("status") boolean status,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        title = source.getTitle();
        deadline = getLocalDateString(source.getDeadline());
        status = source.getStatus();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there are any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }

        // TODO: Handle exceptions more gracefully
        if (title == null) {
            throw new IllegalValueException("Task's title field is missing !");
        }

        // TODO: isValidTitle... Deadline ... Tags

        final String modelTitle = this.title;

        if (deadline == null) {
            throw new IllegalValueException("Task's deadline field is missing !");
        }

        if (!isLocalDateString(deadline)) {
            throw new IllegalValueException("Incorrect LocalDate format !");
        }

        final LocalDate modelDeadline = getLocalDate(this.deadline);

        final boolean modelStatus = this.status;

        final Set<Tag> modelTags = new HashSet<>(taskTags);
        return new Task(modelTitle, modelDeadline, modelStatus, modelTags);
    }
}
