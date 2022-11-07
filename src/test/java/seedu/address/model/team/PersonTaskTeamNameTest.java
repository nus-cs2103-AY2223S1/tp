package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PersonTaskTeamNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TeamName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new TeamName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> TeamName.isValidName(null));

        // invalid name
        assertFalse(TeamName.isValidName("")); // empty string
        assertFalse(TeamName.isValidName(" ")); // spaces only
        assertFalse(TeamName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(TeamName.isValidName("Front*end")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TeamName.isValidName("social media")); // alphabets only
        assertTrue(TeamName.isValidName("12345")); // numbers only
        assertTrue(TeamName.isValidName("backend 2")); // alphanumeric characters
        assertTrue(TeamName.isValidName("Capital Tan")); // with capital letters
        assertTrue(TeamName.isValidName("Site Reliability and Database Management")); // long names
        assertTrue(TeamName.isValidName("Frontend (Payment System)"));
    }
}
