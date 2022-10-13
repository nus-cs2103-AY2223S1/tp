package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignmentdetails.AssignmentDetails;

/**
 * Jackson-friendly version of {@link AssignmentDetails}.
 */
class JsonAdaptedAssignmentDetails {

    private final String assignmentDetails;

    /**
     * Constructs a {@code JsonAdaptedAssignmentDetails} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedAssignmentDetails(String assignmentDetails) {
        this.assignmentDetails = assignmentDetails;
    }

    /**
     * Converts a given {@code AssignmentDetails} into this class for Jackson use.
     */
    public JsonAdaptedAssignmentDetails(AssignmentDetails source) {
        assignmentDetails = source.assignmentDetails;
    }

    @JsonValue
    public String getAssignmentDetails() {
        return assignmentDetails;
    }

    /**
     * Converts this Jackson-friendly adapted assignmentDetails object into the model's
     * {@code AssignmentDetails} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignmentDetails.
     */
    public AssignmentDetails toModelType() throws IllegalValueException {
        if (!AssignmentDetails.areValidAssignmentDetails(assignmentDetails)) {
            throw new IllegalValueException(AssignmentDetails.MESSAGE_CONSTRAINTS);
        }
        return new AssignmentDetails(assignmentDetails);
    }

}
