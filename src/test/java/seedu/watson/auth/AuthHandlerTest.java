package seedu.watson.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthHandlerTest {

    private final String testUsername = "admin";
    private final String testPassword = "admin";

    @Test
    public void testCheckCredentials() {
        assertEquals(testUsername, Credentials.getDebugUsername());
        assertEquals(testPassword, Credentials.getDebugPassword());
    }
}
