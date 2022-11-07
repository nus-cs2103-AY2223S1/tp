package seedu.watson.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AuthHandlerTest {

    private final String testUsername = "admin";
    private final String testPassword = "admin";

    @Test
    public void testCheckCredentials() {
        assertEquals(testUsername, Credentials.getDebugUsername());
        assertEquals(testPassword, Credentials.getDebugPassword());

        assertTrue(AuthHandler.checkCredentials(testUsername, testPassword));

        assertFalse(AuthHandler.checkCredentials("admin", "wrongPassword"));
    }
}
