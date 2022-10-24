package seedu.phu.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setInternshipBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setInternshipBookFilePath(null));
    }

    @Test
    public void equals() {
        UserPrefs userPref = new UserPrefs();
        assertTrue(userPref.equals(userPref));
        assertFalse(userPref.equals(null));
    }

    @Test
    public void hashcode() {
        UserPrefs userPref = new UserPrefs();
        assertEquals(userPref.hashCode(), new UserPrefs().hashCode());
    }
}

