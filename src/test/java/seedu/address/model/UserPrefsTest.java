package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

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
    public void equals() {
        // same values -> returns true
        UserPrefs userPrefs = new UserPrefs();
        UserPrefs userPrefsCopy = new UserPrefs();
        assertTrue(userPrefs.equals(userPrefsCopy));

        // same object -> returns true
        assertTrue(userPrefs.equals(userPrefs));

        // null -> returns false
        assertFalse(userPrefs.equals(null));

        // different types -> returns false
        assertFalse(userPrefs.equals(5));

        // different guiSettings
        userPrefsCopy.setGuiSettings(new GuiSettings(1200, 800, 0, 0));
        assertFalse(userPrefs.equals(userPrefsCopy));
    }

    @Test
    public void hashcode() {
        // same values -> returns same hashcode
        UserPrefs userPrefs = new UserPrefs();
        UserPrefs userPrefsCopy = new UserPrefs();
        assertEquals(userPrefs.hashCode(), userPrefsCopy.hashCode());

        // different guiSettings -> returns different hashcode
        userPrefsCopy.setGuiSettings(new GuiSettings(1200, 800, 0, 0));
        assertNotEquals(userPrefs.hashCode(), userPrefsCopy.hashCode());

        // different addressBookFilePath -> returns different hashcode
        userPrefsCopy = new UserPrefs();
        userPrefsCopy.setAddressBookFilePath(Path.of(""));
        assertNotEquals(userPrefs.hashCode(), userPrefsCopy.hashCode());
    }

}
