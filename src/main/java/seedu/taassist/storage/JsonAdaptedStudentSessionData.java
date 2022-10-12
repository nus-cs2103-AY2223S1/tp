package seedu.taassist.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.model.student.StudentSessionData;

/**
 * Json-friendly version of {@link StudentSessionData}.
 */
class JsonAdaptedStudentSessionData {

    private final double grade;

    /**
     * Constructs a {@code JsonAdapterStudentSessionData} with the given {@code grade}.
     */
    @JsonCreator
    public JsonAdaptedStudentSessionData(@JsonProperty("grade") double grade) {
        this.grade = grade;
    }

    /**
     * Converts a given {@code StudentSessionData} into this class for Jackson use.
     */
    public JsonAdaptedStudentSessionData(StudentSessionData source) {
        grade = source.getGrade();
    }

    /**
     * Converts this Json-friendly adapted module class object into the model's {@code StudentSessionData} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public StudentSessionData toModelType() throws IllegalValueException {
        return new StudentSessionData(grade);
    }

}
