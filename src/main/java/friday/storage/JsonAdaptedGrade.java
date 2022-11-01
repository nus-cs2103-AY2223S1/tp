package friday.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import friday.commons.exceptions.IllegalValueException;
import friday.model.grades.Grade;

/**
 * Jackson-friendly version of {@link Grade}.
 */
public class JsonAdaptedGrade {

    private final String score;

    /**
     * Constructs a {@code JsonAdaptedGrade} with the given {@code score}.
     */
    @JsonCreator
    public JsonAdaptedGrade(String score) {
        this.score = score;
    }

    /**
     * Converts a given {@code Grade} into this class for Jackson use.
     */
    public JsonAdaptedGrade(Grade source) {
        score = source.getScore();
    }

    @JsonValue
    public String getGradeScore() {
        return score;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Grade} object.
     *
     * @param index the identity of the assessment or examination
     * @throws IllegalValueException if there were any data constraints violated in the adapted grade.
     */
    public Grade toModelType(int index) throws IllegalValueException {
        if (!Grade.isValidScore(score)) {
            throw new IllegalValueException(Grade.MESSAGE_CONSTRAINTS);
        }
        switch (index) {

        case 0:
            return new Grade("RA1", score);

        case 1:
            return new Grade("RA2", score);

        case 2:
            return new Grade("Midterm", score);

        case 3:
            return new Grade("Practical", score);

        case 4:
            return new Grade("Finals", score);

        default:
            return new Grade("test", score);

        }
    }

}
