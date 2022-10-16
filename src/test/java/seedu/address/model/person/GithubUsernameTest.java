package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GithubUsernameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GithubUsername(null, true));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidUsername = "";
        assertThrows(IllegalArgumentException.class, () -> new GithubUsername(invalidUsername, true));
    }

    @Test
    public void isValidName() {
        // null username
        assertThrows(NullPointerException.class, () -> GithubUsername.isValidUsername(null));

        // invalid github username
        assertFalse(GithubUsername.isValidUsername("")); // empty string
        assertFalse(GithubUsername.isValidUsername(" ")); // spaces only
        assertFalse(GithubUsername.isValidUsername(" test")); // firstCharacter cannot be whitespace
        assertFalse(GithubUsername.isValidUsername("-test")); // firstCharacter cannot be hyphen
        assertFalse(GithubUsername.isValidUsername("test-")); // LastCharacter cannot be hyphen
        assertFalse(GithubUsername.isValidUsername("tes$t")); // cannot have special symbol other than hyphen
        assertFalse(GithubUsername.isValidUsername("a".repeat(40))); // more than 39 char

        // valid github username
        assertTrue(GithubUsername.isValidUsername("peter")); // alphabets only
        assertTrue(GithubUsername.isValidUsername("12345")); // numbers only
        assertTrue(GithubUsername.isValidUsername("peter1")); // alphanumeric characters
        assertTrue(GithubUsername.isValidUsername("peter-1")); // alphanumeric characters with hyphen
        assertTrue(GithubUsername.isValidUsername("Capital")); // with capital letters
        assertTrue(GithubUsername.isValidUsername("DavidBeckham12345testing")); // long names
    }
}
