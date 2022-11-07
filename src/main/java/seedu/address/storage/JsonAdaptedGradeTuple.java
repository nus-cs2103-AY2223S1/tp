package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.grade.Grade;
import seedu.address.model.grade.GradeKey;
import seedu.address.model.student.GradeMap;

/**
 * Jackson-friendly of a {@link GradeMap} tuple
 */
class JsonAdaptedGradeTuple {

    private final JsonAdaptedGradeKey gradeKey;
    private final JsonAdaptedGrade grade;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedGradeTuple(@JsonProperty("gradeKey") JsonAdaptedGradeKey gradeKey,
                               @JsonProperty("grade") JsonAdaptedGrade grade) {
        this.gradeKey = gradeKey;
        this.grade = grade;
    }

    /**
     * Converts a given {@code (GradeKey, Grade)} into this class for Jackson use.
     */
    public JsonAdaptedGradeTuple(GradeKey gradeKey, Grade grade) {
        this.gradeKey = new JsonAdaptedGradeKey(gradeKey);
        this.grade = new JsonAdaptedGrade(grade);
    }

    public JsonAdaptedGradeKey getGradeKey() {
        return gradeKey;
    }

    public JsonAdaptedGrade getGrade() {
        return grade;
    }
}
