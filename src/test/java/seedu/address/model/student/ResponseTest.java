package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ResponseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Response(null));
    }

    @Test
    public void isValidResponse() {
        // null Response
        assertThrows(NullPointerException.class, () -> Response.isValidResponse(null));

        // invalid Responses
        assertFalse(Response.isValidResponse("")); // empty string
        assertFalse(Response.isValidResponse("  ")); // spaces only
        assertFalse(Response.isValidResponse("Response")); // non-numeric
        assertFalse(Response.isValidResponse("909hihi")); // alphabets within digits
        assertFalse(Response.isValidResponse("test123")); // alphabet before digit
        assertFalse(Response.isValidResponse("9 0")); // spaces within digits

        // valid Responses
        assertTrue(Response.isValidResponse("1")); // 1 digit
        assertTrue(Response.isValidResponse("999")); // 3 digits
        assertTrue(Response.isValidResponse("2392839283")); // many digits
    }
}
