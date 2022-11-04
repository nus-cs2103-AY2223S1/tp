package seedu.clinkedin.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

    @Test
    public void equalityTest() {
        UserPrefs userPrefs1 = new UserPrefs();
        UserPrefs userPrefs2 = new UserPrefs();

        assertTrue(userPrefs1.equals(userPrefs1));
        assertFalse(userPrefs1.equals(null));
        assertFalse(userPrefs1.equals(5));
        assertTrue(userPrefs1.equals(userPrefs2));

    }

    @Test
    public void hashCodeTest() {
        UserPrefs userPrefs1 = new UserPrefs();
        int one = userPrefs1.hashCode();
        assertTrue(one == one);
    }
}
