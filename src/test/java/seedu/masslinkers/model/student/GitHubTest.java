package seedu.masslinkers.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.masslinkers.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GitHubTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GitHub(null));
    }

    @Test
    public void constructor_invalidGithub_throwsIllegalArgumentException() {
        String invalidGithub = "";
        assertThrows(IllegalArgumentException.class, () -> new GitHub(invalidGithub));
    }

    @Test
    public void isValidGitHub() {
        // null GitHub
        assertThrows(NullPointerException.class, () -> GitHub.isValidGitHub(null));

        // invalid GitHub
        assertFalse(GitHub.isValidGitHub("")); // empty string
        assertFalse(GitHub.isValidGitHub(" ")); // spaces only
        assertFalse(GitHub.isValidGitHub("-hello")); // starts with hyphen
        assertFalse(GitHub.isValidGitHub("ld--dfk-fesd")); // > 1 consecutive hyphen
        assertFalse(GitHub.isValidGitHub("lddfkfesd--")); // > 1 consecutive hyphen
        assertFalse(GitHub.isValidGitHub("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm")); // >31 chars
        assertFalse(GitHub.isValidGitHub("lopi&e")); // special chars that are not hyphens

        // valid GitHub
        assertTrue(GitHub.isValidGitHub("1")); // exactly 1 number
        assertTrue(GitHub.isValidGitHub("a")); // exactly 1 letter
        assertTrue(GitHub.isValidGitHub("lopie-")); // legacy github username
        assertTrue(GitHub.isValidGitHub("jammy12")); // words and numbers
        assertTrue(GitHub.isValidGitHub("JAMMdfg")); // caps
        assertTrue(GitHub.isValidGitHub("12345")); // numbers
        assertTrue(GitHub.isValidGitHub("jdf-3wj")); // one hyphen
        assertTrue(GitHub.isValidGitHub("ld-dfk-fesd")); // > 1 hyphen
    }
}
