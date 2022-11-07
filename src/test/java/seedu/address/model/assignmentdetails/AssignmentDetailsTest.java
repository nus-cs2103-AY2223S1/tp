package seedu.address.model.assignmentdetails;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssignmentDetailsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignmentDetails(null));
    }

    @Test
    public void constructor_invalidAssignmentDetail_throwsIllegalArgumentException() {
        String invalidAssignmentDetail = "";
        assertThrows(IllegalArgumentException.class, () -> new AssignmentDetails(invalidAssignmentDetail));
    }

    @Test
    public void isValidAssignmentDetailsDescription() {
        // null assignment details
        assertThrows(NullPointerException.class, () -> AssignmentDetails.areValidAssignmentDetails(null));

        // invalid assignment details
        assertFalse(AssignmentDetails.areValidAssignmentDetails(" ")); // Empty string

        // valid assignment details
        assertTrue(AssignmentDetails.areValidAssignmentDetails("Assignment 1")); // Spaces allowed
        assertTrue(AssignmentDetails.areValidAssignmentDetails("Assignment1"));
        assertTrue(AssignmentDetails.areValidAssignmentDetails("Tutorial 1 Assignment 1")); // Multiple words
        assertTrue(AssignmentDetails.areValidAssignmentDetails("v1.3")); // Non-alphanumerics allowed
    }
}
