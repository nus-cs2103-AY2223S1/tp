package seedu.application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.application.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.GuiSettings;

public class UserPrefsTest {

    @Test
    public void resetData_allInOrder_success() {
        UserPrefs userPrefs = new UserPrefs();
        UserPrefs userPrefs2 = new UserPrefs();

        GuiSettings guiSettings = new GuiSettings(1000, 1000, 0, 0);
        Path applicationBookFilePath = Paths.get("data", "testpath.json");
        SortSetting sortSetting = SortSetting.BY_COMPANY;
        userPrefs2.setGuiSettings(guiSettings);
        userPrefs2.setApplicationBookFilePath(applicationBookFilePath);
        userPrefs2.setSortSetting(sortSetting);

        userPrefs.resetData(userPrefs2);

        assertEquals(guiSettings, userPrefs.getGuiSettings());
        assertEquals(applicationBookFilePath, userPrefs.getApplicationBookFilePath());
        assertEquals(sortSetting, userPrefs.getSortSetting());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setApplicationBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setApplicationBookFilePath(null));
    }

    @Test
    public void setSortSetting_nullSortSetting_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setSortSetting(null));
    }

    @Test
    public void equals() {
        UserPrefs userPrefs = new UserPrefs();
        UserPrefs userPrefs2;

        // same object -> returns true
        assertEquals(userPrefs, userPrefs);

        // same values -> returns true
        UserPrefs userPrefsCopy = new UserPrefs(userPrefs);
        assertEquals(userPrefsCopy, userPrefs);

        // different types -> returns false
        assertNotEquals(1, userPrefs);

        // null -> returns false
        assertNotEquals(null, userPrefs);

        // different guiSettings -> returns false
        userPrefs2 = new UserPrefs(userPrefs);
        userPrefs2.setGuiSettings(new GuiSettings(0, 0, 0, 0));
        assertNotEquals(userPrefs2, userPrefs);

        // different applicationBookFilePath -> returns false
        userPrefs2 = new UserPrefs(userPrefs);
        userPrefs2.setApplicationBookFilePath(Paths.get("data", "testpath.json"));
        assertNotEquals(userPrefs2, userPrefs);

        // different sortSetting -> returns false
        userPrefs2 = new UserPrefs(userPrefs);
        userPrefs2.setSortSetting(SortSetting.BY_DATE_REVERSE);
        assertNotEquals(userPrefs2, userPrefs);
    }

}
