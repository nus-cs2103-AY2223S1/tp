package seedu.address.storage;

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
import seedu.address.model.task.Module;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String name;
    private final String module;
    private final String deadline;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final Boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("module") String module,
                             @JsonProperty("deadline") String deadline,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("isDone") Boolean isDone) {
        this.name = name;
        this.module = module;
        this.deadline = deadline;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Task source) {
        name = source.getName().fullName;
        module = source.getModule().fullName;
        deadline = source.getDeadline().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        isDone = source.isDone();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (module == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Module.class.getSimpleName()));
        }

        if (!Module.isValidModule(module)) {
            throw new IllegalValueException(Module.MESSAGE_CONSTRAINTS);
        }
        final Module modelModule = new Module(module);

        if (deadline == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (isDone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "isDone"));
        }

        return new Task(modelName, modelModule, modelDeadline, modelTags, this.isDone);
    }

}
