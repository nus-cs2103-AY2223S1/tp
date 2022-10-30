package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.person.GradeProgress;


/**
 * Jackson-friendly version of {@link GradeProgress}.
 */
class JsonAdaptedGradeProgress {

    private final String gradeProgress;

    /**
     * Constructs a {@code JsonAdaptedGradeProgress} with the given {@code gradeProgress}.
     */
    @JsonCreator
    public JsonAdaptedGradeProgress(String gradeProgress) {
        this.gradeProgress = gradeProgress;
    }

    /**
     * Converts a given {@code GradeProgress} into this class for Jackson use.
     */
    public JsonAdaptedGradeProgress(GradeProgress source) {
        gradeProgress = source.value;
    }

    @JsonValue
    public String getTagName() {
        return gradeProgress;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Homework} object.
     */
    public GradeProgress toModelType() {
        return new GradeProgress(gradeProgress);
    }

}
