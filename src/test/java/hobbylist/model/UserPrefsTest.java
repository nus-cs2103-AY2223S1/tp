package hobbylist.model;

import org.junit.jupiter.api.Test;

import hobbylist.testutil.Assert;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        Assert.assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAliasSettings_nullAliasSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        Assert.assertThrows(NullPointerException.class, () -> userPref.setAliasSettings(null));
    }

    @Test
    public void setHobbyListFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        Assert.assertThrows(NullPointerException.class, () -> userPrefs.setHobbyListFilePath(null));
    }

}
