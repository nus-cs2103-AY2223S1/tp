package seedu.travelr.model;

import org.junit.jupiter.api.Test;
import seedu.address.model.UserPrefs;

import static seedu.address.testutil.Assert.assertThrows;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        seedu.address.model.UserPrefs userPref = new seedu.address.model.UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        seedu.address.model.UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

}
