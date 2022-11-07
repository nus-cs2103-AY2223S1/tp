package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Name;
import seedu.address.model.student.TutorialGroup;
/**
 * Jackson-friendly version of {@link TutorialGroup}.
 */
class JsonAdaptedTutorialGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutorial group's %s field is missing!";

    private final String name;

    //private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTutorialGroup} with the given tutorial group details.
     */
    @JsonCreator
    public JsonAdaptedTutorialGroup(@JsonProperty("tutorialName") String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code TutorialGroup} into this class for Jackson use.
     */
    public JsonAdaptedTutorialGroup(TutorialGroup source) {
        name = source.toString();
    }

    /**
     * Converts this Jackson-friendly adapted tutorial group object into the model's {@code TutorialGroup} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public TutorialGroup toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        return new TutorialGroup(name);
    }

}
