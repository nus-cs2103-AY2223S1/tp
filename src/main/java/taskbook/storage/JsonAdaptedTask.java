package taskbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import taskbook.commons.exceptions.IllegalValueException;
import taskbook.model.task.Task;



/**
 * Jackson-friendly version of {@link Task}.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = JsonAdaptedTodo.class, name = "Todo"),
    @JsonSubTypes.Type(value = JsonAdaptedEvent.class, name = "Event"),
    @JsonSubTypes.Type(value = JsonAdaptedDeadline.class, name = "Deadline")
})
public abstract class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    protected final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String name;
    private final String assignment;
    private final String description;
    private final boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("assignment") String assignment,
                           @JsonProperty("description") String description, @JsonProperty("isDone") boolean isDone,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.assignment = assignment;
        this.description = description;
        this.isDone = isDone;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName().fullName;
        assignment = source.getAssignment().name();
        description = source.getDescription().description;
        isDone = source.isDone();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    public String getName() {
        return name;
    }

    public String getAssignment() {
        return assignment;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public List<JsonAdaptedTag> getTags() {
        return tagged;
    }

    /**
     * Converts this Jackson-friendly adapted Task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public abstract Task toModelType() throws IllegalValueException;
}
