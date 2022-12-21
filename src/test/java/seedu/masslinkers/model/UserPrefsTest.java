package seedu.masslinkers.model;

import static seedu.masslinkers.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
//@@author
public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setMassLinkersFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setMassLinkersFilePath(null));
    }

}
