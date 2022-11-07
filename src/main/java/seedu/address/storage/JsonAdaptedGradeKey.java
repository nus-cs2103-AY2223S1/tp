package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.grade.GradeKey;
/**
 * Jackson-friendly version of {@link GradeKey}.
 */
class JsonAdaptedGradeKey {

    private final JsonAdaptedStudent student;
    private final JsonAdaptedTask task;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given student and task details.
     */
    @JsonCreator
    public JsonAdaptedGradeKey(@JsonProperty("student") JsonAdaptedStudent student,
                           @JsonProperty("task") JsonAdaptedTask task) {
        this.student = student;
        this.task = task;
    }

    /**
     * Converts a given {@code GradeKey} into this class for Jackson use.
     */
    public JsonAdaptedGradeKey(GradeKey source) {
        student = new JsonAdaptedStudent(source.getStudent());
        task = new JsonAdaptedTask(source.getTask());
    }

    /**
     * Converts this Jackson-friendly adapted gradeKey object into the model's {@code GradeKey} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public GradeKey toModelType() throws IllegalValueException {
        return new GradeKey(student.toModelType(), task.toModelType());
    }
}
