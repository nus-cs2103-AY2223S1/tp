package modtrekt.model;

import static modtrekt.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setModuleListFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
<<<<<<< HEAD
        assertThrows(NullPointerException.class, () -> userPrefs.setTaskBookFilePath(null));
=======
        assertThrows(NullPointerException.class, () -> userPrefs.setModuleListFilePath(null));
>>>>>>> junhao/HoJunHao2000/week-8/implement-module-commands
    }

}
