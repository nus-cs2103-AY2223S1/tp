package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Survey;

/**
 * Jackson-friendly version of {@link Survey}.
 */
class JsonAdaptedSurvey {

    private final String surveyName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedSurvey(String surveyName) {
        this.surveyName = surveyName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedSurvey(Survey source) {
        surveyName = source.survey;
    }

    @JsonValue
    public String getTagName() {
        return surveyName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Survey toModelType() throws IllegalValueException {
        if (!Survey.isValidSurvey(surveyName)) {
            throw new IllegalValueException(Survey.MESSAGE_CONSTRAINTS);
        }
        return new Survey(surveyName);
    }

}
