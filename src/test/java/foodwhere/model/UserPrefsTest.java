package foodwhere.model;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.GuiSettings;

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
    public void equals_notEqualCases_isCorrect() {
        UserPrefs userPrefs = new UserPrefs();
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);

        // different object with same settings -> return true
        assertTrue(userPrefs.equals(oldUserPrefs));

        // null -> return false
        assertFalse(userPrefs.equals(null));

        // same object -> return true
        assertTrue(userPrefs.equals(userPrefs));

        // different object with different settings -> return false
        UserPrefs userPrefsDifferent = new UserPrefs();
        userPrefsDifferent.setAddressBookFilePath(Paths.get("address/book/file/path"));
        assertFalse(userPrefs.equals(userPrefsDifferent));

        UserPrefs userPrefsDifferent2 = new UserPrefs();
        userPrefsDifferent2.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        assertFalse(userPrefs.equals(userPrefsDifferent2));

        UserPrefs userPrefsDifferent3 = new UserPrefs();
        userPrefsDifferent3.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefsDifferent3.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        assertFalse(userPrefs.equals(userPrefsDifferent3));

        assertFalse(userPrefs.equals(5));
    }

    @Test
    public void hashCode_test() {
        UserPrefs userPrefs = new UserPrefs();

        // same object -> hash codes are equal
        assertEquals(userPrefs.hashCode(), userPrefs.hashCode());

        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);

        // different object with same settings -> hash codes are equal
        assertEquals(oldUserPrefs.hashCode(), userPrefs.hashCode());

        // different object with different path -> hash codes are not equal
        UserPrefs userPrefsDifferent = new UserPrefs();
        userPrefsDifferent.setAddressBookFilePath(Paths.get("address/book/file/path"));
        assertNotEquals(userPrefsDifferent.hashCode(), userPrefs.hashCode());

        // different object with different gui settings -> hash codes are not equal
        UserPrefs userPrefsDifferent2 = new UserPrefs();
        userPrefsDifferent2.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        assertNotEquals(userPrefsDifferent2.hashCode(), userPrefs.hashCode());

        // different objects with different path and gui settings -> hash codes are not equal
        UserPrefs userPrefsDifferent3 = new UserPrefs();
        userPrefsDifferent3.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefsDifferent3.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        assertNotEquals(userPrefsDifferent3.hashCode(), userPrefs.hashCode());
    }
}
