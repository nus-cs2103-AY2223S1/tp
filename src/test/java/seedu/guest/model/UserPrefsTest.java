package seedu.guest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.guest.testutil.Assert.assertThrows;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.guest.commons.core.GuiSettings;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setGuestBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setGuestBookFilePath(null));
    }

    @Test
    public void getGuiSettings_success() {
        UserPrefs userPrefs = new UserPrefs();
        assertEquals(userPrefs.getGuiSettings(), new UserPrefs().getGuiSettings());
    }

    @Test
    public void getGuestBookFilePath_success() {
        UserPrefs userPrefs = new UserPrefs();
        assertEquals(userPrefs.getGuestBookFilePath(), new UserPrefs().getGuestBookFilePath());
    }

    @Test
    public void equals() {
        UserPrefs userPref = new UserPrefs();

        // same object -> returns true
        assertTrue(userPref.equals(userPref));

        // null -> returns false
        assertFalse(userPref.equals(null));

        // different GuiSettings -> returns false
        UserPrefs editedUserPref = new UserPrefs();
        editedUserPref.setGuiSettings(new GuiSettings(1.0, 1.0, 1, 1));
        assertFalse(userPref.equals(editedUserPref));

        // different GuestBookFilePath -> returns false
        editedUserPref = new UserPrefs();
        editedUserPref.setGuestBookFilePath(Paths.get("address/book/file/path"));
        assertFalse(userPref.equals(editedUserPref));
    }

    @Test
    public void hashcode() {
        UserPrefs userPref = new UserPrefs();

        // same object -> returns true
        assertEquals(userPref.hashCode(), userPref.hashCode());

        // different GuiSettings -> returns false
        UserPrefs editedUserPref = new UserPrefs();
        editedUserPref.setGuiSettings(new GuiSettings(1.0, 1.0, 1, 1));
        assertNotEquals(userPref.hashCode(), editedUserPref.hashCode());

        // different GuestBookFilePath -> returns false
        editedUserPref = new UserPrefs();
        editedUserPref.setGuestBookFilePath(Paths.get("address/book/file/path"));
        assertNotEquals(userPref.hashCode(), editedUserPref.hashCode());
    }
}
