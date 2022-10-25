package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Survey;

/**
 * Jackson-friendly version of {@link Survey}.
 */
class JsonAdaptedSurvey {

    private final String surveyName;
    private final boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedSurvey} with the given {@code surveyName}.
     */
    @JsonCreator
    public JsonAdaptedSurvey(@JsonProperty("surveyName") String surveyName, @JsonProperty("isDone") boolean isDone) {
        this.surveyName = surveyName;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Survey} into this class for Jackson use.
     */
    public JsonAdaptedSurvey(Survey source) {
        surveyName = source.survey;
        isDone = source.isDone;
    }

    /*
     * @JsonValue public String getSurveyInfo() { return surveyName + " - " +
     * isDone; }
     */

    /**
     * Converts this Jackson-friendly adapted tag object into the model's
     * {@code Survey} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted tag.
     */
    public Survey toModelType() throws IllegalValueException {
        if (!Survey.isValidSurvey(surveyName)) {
            throw new IllegalValueException(Survey.MESSAGE_CONSTRAINTS);
        }
        return new Survey(surveyName, isDone);
    }

}
