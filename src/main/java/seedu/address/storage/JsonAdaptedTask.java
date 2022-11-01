package seedu.address.storage;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Id;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String description;
    private final String deadline;
    private final Boolean isDone;
    private final Boolean isArchived;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String id;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("description") String description, @JsonProperty("deadline") String deadline,
                            @JsonProperty("isDone") Boolean isDone, @JsonProperty("isArchived") Boolean isArchived,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged, @JsonProperty("id") String id) {
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
        this.isArchived = isArchived;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.id = id;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        description = source.getDescription().taskDescription;
        deadline = source.getDeadline().value;
        isDone = source.getCompletionStatus();
        isArchived = source.getArchivalStatus();

        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        id = source.getId().toString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        Deadline parsedDeadline;
        try {
            parsedDeadline = new Deadline(deadline);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = parsedDeadline;

        if (isDone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Boolean.class.getSimpleName()));
        }
        final Boolean modelIsDone = isDone;

        if (isArchived == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Boolean.class.getSimpleName()));
        }
        final Boolean modelIsArchived = isArchived;

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Id.class.getSimpleName()));
        }
        final Id modelId = new Id(Integer.parseInt(id));

        final Set<Tag> modelTags = new HashSet<>(taskTags);
        return new Task(modelDescription, modelDeadline, modelIsDone, modelIsArchived, modelTags, modelId);
    }
}
