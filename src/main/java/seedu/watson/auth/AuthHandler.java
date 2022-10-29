package seedu.watson.auth;

/**
 *
 */
public class AuthHandler {

    // HOW IT WORKS
    // 1) Users will be given a hardcoded username/password
    // 2) When the user logs in, the app will check its credentials against the hardcoded ones
    // 3) If the credentials are correct, the app will allow the user to access the app
    public AuthHandler() {
    }

    /**
     * Checks if the given username and password are correct.
     *
     * @param username The username to check.
     * @param password The password to check.
     * @return True if the username and password are correct, false otherwise.
     */
    public static boolean checkCredentials(String username, String password) {
        return username.equals(Credentials.getDebugUsername())
               && password.equals(Credentials.getDebugPassword());
    }
}
