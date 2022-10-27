package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask extends JsonAdaptedAbstractDisplayItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    public static final String INVALID_FIELD_MESSAGE_FORMAT = "Task's %s field is invalid!";

    private final String description;
    private final String localDateTime;
    private String progress;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("description") String description, @JsonProperty("progress") String progress,
                           @JsonProperty("localDateTime") String localDateTime, @JsonProperty("name") String name,
                           @JsonProperty("uid") String uid, @JsonProperty("tags") List<JsonAdaptedTag> tags,
                           @JsonProperty("attributes") List<JsonAdaptedAbstractAttribute> attributes) {
        super(name, uid, attributes, tags);
        this.description = description;
        this.localDateTime = localDateTime;
        this.progress = progress;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        super(source.getName().fullName, source.getUuid().toString(),
                source.getAttributes().stream()
                        .map(JsonAdaptedAbstractAttribute::new)
                        .collect(Collectors.toList()),
                source.getTags().stream()
                        .map(JsonAdaptedTag::new)
                        .collect(Collectors.toList()));
        requireNonNull(source);
        description = source.getDescriptionContent();
        LocalDateTime completedTime = source.getCompletedTime();
        localDateTime = completedTime == null ? "" : completedTime.toString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's
     * {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        final List<Attribute> modelAttributes = new ArrayList<>();

        // Exception handling is not supported in Java streams.
        for (JsonAdaptedTag tag : getTags()) {
            taskTags.add(tag.toModelType());
        }

        for (JsonAdaptedAbstractAttribute attribute : getAttributes()) {
            modelAttributes.add(attribute.toModelType());
        }

        String name = getName();
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        final Name modelName = new Name(name);
        final Set<Tag> modelTags = new HashSet<>(taskTags);

        LocalDateTime modelLocalDateTime = null;
        try {
            if (!localDateTime.isEmpty()) {
                modelLocalDateTime = LocalDateTime.parse(localDateTime);
            }
        } catch (DateTimeParseException pe) {
            throw new IllegalValueException(String.format(INVALID_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        Name taskName = new Name(modelName.getAttributeContent());
        Task task = new Task(taskName, description, progress, modelLocalDateTime);
        task.setTags(modelTags);
        modelAttributes.forEach(attribute -> task.addAttribute(attribute));
        return task;
    }
}
