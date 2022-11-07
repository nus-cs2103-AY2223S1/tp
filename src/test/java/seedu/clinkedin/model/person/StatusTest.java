package seedu.clinkedin.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null name
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid name
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only
        assertFalse(Status.isValidStatus("^")); // only non-alphanumeric characters
        assertFalse(Status.isValidStatus("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Status.isValidStatus("application received")); // alphabets only
        assertTrue(Status.isValidStatus("12345")); // numbers only
        assertTrue(Status.isValidStatus("2nd round oa")); // alphanumeric characters
        assertTrue(Status.isValidStatus("Application Received")); // with capital letters
        assertTrue(Status.isValidStatus("Accepted but applicant wants to negotiate compensation package"));
        // long names
    }

    @Test
    public void status_toString() {
        String statusString = "Application";
        Status status1 = new Status(statusString);
        Status status2 = new Status("Application");
        assertTrue(status1.toString().equals(status2.toString()));
        assertTrue(status1.toString().equals(statusString));
        assertTrue(status2.toString().equals(statusString));
    }

    @Test
    public void equalityTests() {
        Status status1 = new Status("Application");
        Status status2 = new Status("Application");
        assertTrue(status1.equals(status2));
        assertTrue(status1.equals(status1));
        assertFalse(status1.equals(null));
        assertFalse(status1.equals(5));
    }

    @Test
    public void hashcodeTests() {
        Status status1 = new Status("OA");
        Status status2 = new Status("OA");
        int hashcode1 = status1.hashCode();
        int hashcode2 = status2.hashCode();
        assertTrue(hashcode1 == hashcode2);
        assertTrue(hashcode1 == hashcode1);
    }
}
