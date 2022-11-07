package seedu.guest.model.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RequestTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Request(null));
    }

    @Test
    public void constructor_invalidRequest_throwsIllegalArgumentException() {
        String invalidRequest = "hi".repeat(501);
        assertThrows(IllegalArgumentException.class, () -> new Request(invalidRequest));
    }

    @Test
    public void isValidRequest() {
        // null request
        assertThrows(NullPointerException.class, () -> Request.isValidRequest(null));
        assertThrows(IllegalArgumentException.class, () -> Request.isValidRequest("L".repeat(-1)));

        // invalid request
        assertFalse(Request.isValidRequest("1".repeat(501))); // over 500 characters, near boundary values

        // valid request
        assertTrue(Request.isValidRequest(" ".repeat(5000))); // space will be trimmed to empty
        assertTrue(Request.isValidRequest("H".repeat(499))); // 499 characters (near boundary values)
        assertTrue(Request.isValidRequest("E".repeat(500))); // 500 characters (near boundary values)
        assertTrue(Request.isValidRequest("E".repeat(0))); // 0 characters (near boundary values)
    }

    @Test
    public void hashcode() {
        Request tempRequest = new Request("change bed");

        // same values -> return true
        assertEquals(tempRequest, new Request("change bed"));

        // different values -> return false
        assertNotEquals(tempRequest, new Request("change bed 2nd"));
    }
}
