package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.testutil.Assert.assertThrows;

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
    public void equalsTest() {
        UserPrefs userPrefs = new UserPrefs();
        assertTrue(userPrefs.equals(userPrefs));
        assertFalse(userPrefs.equals(null));
    }

    @Test
    public void hashCodeTest() {
        UserPrefs userPrefs = new UserPrefs();
        assertTrue(userPrefs.hashCode() == userPrefs.hashCode());
    }

}
