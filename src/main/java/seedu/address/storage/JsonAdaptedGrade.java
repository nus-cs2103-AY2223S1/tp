package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.grade.Grade;

/**
 * Jackson-friendly version of {@link Grade}.
 */
class JsonAdaptedGrade {

    public static final String MISSING_STUDENT_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";
    public static final String MISSING_TASK_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final boolean grade;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given grade details.
     */
    @JsonCreator
    public JsonAdaptedGrade(@JsonProperty("grade") boolean grade) {
        this.grade = grade;
    }

    /**
     * Converts a given {@code Grade} into this class for Jackson use.
     */
    public JsonAdaptedGrade(Grade source) {
        this.grade = source == Grade.GRADED;
    }

    /**
     * Converts this Jackson-friendly adapted grade object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted grade.
     */
    public Grade toModelType() throws IllegalValueException {
        return this.grade ? Grade.GRADED : Grade.UNGRADED;
    }

}
