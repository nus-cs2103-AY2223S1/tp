package seedu.hrpro.model;

import static seedu.hrpro.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains test cases for UserPrefs
 */
public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setHrProFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setHrProFilePath(null));
    }

}
