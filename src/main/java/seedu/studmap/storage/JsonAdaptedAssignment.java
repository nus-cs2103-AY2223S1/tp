package seedu.studmap.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.studmap.commons.exceptions.IllegalValueException;
import seedu.studmap.model.student.Assignment;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
class JsonAdaptedAssignment {

    private final String assignmentName;

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given {@code assignmentName}.
     */
    @JsonCreator
    public JsonAdaptedAssignment(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment source) {
        assignmentName = source.getString();
    }

    @JsonValue
    public String getAssignmentName() {
        return assignmentName;
    }

    /**
     * Converts this Jackson-friendly adapted Assignment object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Assignment.
     */
    public Assignment toModelType() throws IllegalArgumentException {
        String[] values = assignmentName.split(":");
        if (values.length != 2
                || !Assignment.isValidAssignmentName(values[0])) {
            throw new IllegalArgumentException(Assignment.MESSAGE_CONSTRAINTS);
        }
        Assignment.Status markingStatus = Assignment.Status.fromString(values[1]);
        return new Assignment(values[0], markingStatus);
    }

}
