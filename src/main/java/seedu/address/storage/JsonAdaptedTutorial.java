package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

/**
 * Jackson-friendly version of {@link Tutorial}.
 */
class JsonAdaptedTutorial {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutorial's %s field is missing!";

    private final String name;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given tutorial details.
     */
    @JsonCreator
    public JsonAdaptedTutorial(@JsonProperty("name") String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code Tutorial} into this class for Jackson use.
     */
    public JsonAdaptedTutorial(Tutorial source) {
        name = source.getName().fullName;
    }

    /**
     * Converts this Jackson-friendly adapted tutorial object into the model's {@code Tutorial} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tutorial.
     */
    public Tutorial toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TutorialName.class.getSimpleName()));
        }
        if (!TutorialName.isValidName(name)) {
            throw new IllegalValueException(TutorialName.MESSAGE_CONSTRAINTS);
        }
        final TutorialName modelName = new TutorialName(name);

        return new Tutorial(modelName);
    }

}
