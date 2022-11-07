package seedu.watson.auth;

/**
 * Contains default logins.
 */
public class Credentials {

    private static final String DEBUG_USERNAME = "admin";
    private static final String DEBUG_PASSWORD = "admin";

    public static String getDebugUsername() {
        return DEBUG_USERNAME;
    }

    public static String getDebugPassword() {
        return DEBUG_PASSWORD;
    }
}
