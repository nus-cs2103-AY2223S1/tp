package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GithubTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Github(null));
    }

    @Test
    public void constructor_invalidGithub_throwsIllegalArgumentException() {
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

    @Test
    public void equals() {
        final Github standardGithub = new Github("ryan-howard");

        // same values -> returns true
        Github githubWithSameName = new Github("ryan-howard");
        assertTrue(standardGithub.equals(githubWithSameName));

        // same object -> returns true
        assertTrue(standardGithub.equals(standardGithub));

        // null -> returns false
        assertFalse(standardGithub.equals(null));

        // different types -> returns false
        assertFalse(standardGithub.equals(new Address("88 West Coast Drive, #01-110")));

        // different usernames -> returns false
        assertFalse(standardGithub.equals(new Github("stanLey-huds0n")));
    }

    @Test
    public void hashcode() {
        final Github standardGithub = new Github("ryan-howard");

        // same values -> returns same hashcode
        Github githubWithSameName = new Github("ryan-howard");
        assertEquals(standardGithub.hashCode(), githubWithSameName.hashCode());

        // different usernames -> returns different hashcode
        assertNotEquals(standardGithub.hashCode(), (new Github("stanLey-huds0n")).hashCode());
    }

}
