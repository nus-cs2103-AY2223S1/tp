package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GithubTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Github(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidGithub = "_";
        assertThrows(IllegalArgumentException.class, () -> new Github(invalidGithub));
    }

    @Test
    public void isValidGithub() {
        // null username
        assertThrows(NullPointerException.class, () -> Github.isValidUsername(null));

        // blank username
        assertFalse(Github.isValidUsername(" ")); // spaces only

        // invalid parts
        assertFalse(Github.isValidUsername("peter_jack")); // underscore in username
        assertFalse(Github.isValidUsername("peter.jack")); // period in username
        assertFalse(Github.isValidUsername("peter+jack")); // pluses in username
        assertFalse(Github.isValidUsername("peter jack")); // spaces in username
        assertFalse(Github.isValidUsername("peter@jack")); // '@' symbol in username
        assertFalse(Github.isValidUsername("peter--jack")); // consecutive hyphens
        assertFalse(Github.isValidUsername("-peterjack")); // username starts with a hyphen
        assertFalse(Github.isValidUsername("peterjack-")); // username ends with a hyphen
        assertFalse(Github.isValidUsername("peterjack-peterjack-peterjack-peterjackk")); // username 40 characters

        // valid username
        assertTrue(Github.isValidUsername("peterjack")); // standard username
        assertTrue(Github.isValidUsername("peter-jack")); // hyphen in local part
        assertTrue(Github.isValidUsername("pet-er-jack")); // multiple non-consecutive hyphens
        assertTrue(Github.isValidUsername("62353535")); // numbers only
        assertTrue(Github.isValidUsername("peter-j4ck")); // mixture of alphanumeric and hyphens
        assertTrue(Github.isValidUsername("peterjack-peterjack-peterjack-peterjack")); // username 39 characters
        assertTrue(Github.isValidUsername("")); // empty username
    }
}
