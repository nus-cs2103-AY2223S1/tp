package seedu.address.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthHandlerTest {

    private final String TEST_USERNAME = "admin";
    private final String TEST_PASSWORD = "admin";
    
    @Test
    public void testCheckCredentials() {
        assertEquals(TEST_USERNAME, Credentials.getDebugUsername());
        assertEquals(TEST_PASSWORD, Credentials.getDebugPassword());
    }
}
