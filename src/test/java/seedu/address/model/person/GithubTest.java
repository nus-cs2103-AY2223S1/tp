package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class GithubTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Github(null));
    }

    @Test
    public void constructor_invalidGithub_throwsIllegalArgumentException() {
        String invalidGithub = "";
        assertThrows(IllegalArgumentException.class, () -> new Github(invalidGithub));
    }

    @Test
    public void isValidGithub() {
        // null address
        assertThrows(NullPointerException.class, () -> Github.isValidGh(null));

        // invalid addresses
        assertFalse(Github.isValidGh("")); // empty string
        assertFalse(Github.isValidGh(" ")); // spaces only
        assertFalse(Github.isValidGh("ben wong")); // more than one word

        // valid github names
        assertTrue(Github.isValidGh("benwong"));
        assertTrue(Github.isValidGh("ben-wong")); // special character inside
        assertTrue(Github.isValidGh("b")); // one character

    }
}
