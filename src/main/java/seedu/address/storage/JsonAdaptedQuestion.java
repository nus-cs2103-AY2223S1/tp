package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.question.Description;
import seedu.address.model.question.ImportantTag;
import seedu.address.model.question.Question;

/**
 * Jackson-friendly version of {@link Question}.
 */
class JsonAdaptedQuestion {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Question's %s field is missing!";

    private final String description;
    private final String isImportant;

    /**
     * Constructs a {@code JsonAdaptedQuestion} with the given question details.
     */
    @JsonCreator
    public JsonAdaptedQuestion(@JsonProperty("description") String description,
                               @JsonProperty("isImportant") String isImportant) {
        this.description = description;
        this.isImportant = isImportant;
    }

    /**
     * Converts a given {@code Question} into this class for Jackson use.
     */
    public JsonAdaptedQuestion(Question source) {
        description = source.getDescription().descriptionString;
        isImportant = Boolean.toString(source.getImportantTag().isImportant);
    }

    /**
     * Converts this Jackson-friendly adapted question object into the model's {@code Question} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted question.
     */
    public Question toModelType() throws IllegalValueException {

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        final Description modelDescription = new Description(description);

        if (isImportant == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ImportantTag.class.getSimpleName()));
        }
        final ImportantTag modelImportantTag = new ImportantTag(Boolean.parseBoolean(isImportant));

        return new Question(modelDescription, modelImportantTag);
    }

}
