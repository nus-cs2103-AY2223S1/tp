package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class GithubUsernameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GithubUsername(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidUsername = "";
        assertThrows(IllegalArgumentException.class, () -> new GithubUsername(invalidUsername));
    }

    @Test
    public void isValidName() {
        // null username
        assertThrows(NullPointerException.class, () -> GithubUsername.isValidUsername(null));

        // invalid github username
        assertFalse(GithubUsername.isValidUsername("")); // empty string
        assertFalse(GithubUsername.isValidUsername(" ")); // spaces only
        assertFalse(GithubUsername.isValidUsername(" test")); // firstCharacter cannot be whitespace
        String veryLongInvalidUsername = "a".repeat(40);
        assertTrue(GithubUsername.isValidUsername(veryLongInvalidUsername)); // more than 39 char

        // valid github username
        assertTrue(GithubUsername.isValidUsername("peter")); // alphabets only
        assertTrue(GithubUsername.isValidUsername("12345")); // numbers only
        assertTrue(GithubUsername.isValidUsername("peter1")); // alphanumeric characters
        assertTrue(GithubUsername.isValidUsername("Capital")); // with capital letters
        assertTrue(GithubUsername.isValidUsername("DavidBeckham12345testing")); // long names
    }
}
