package seedu.trackascholar.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.commons.core.GuiSettings;

public class UserPrefsTest {

    private UserPrefs userPrefs = new UserPrefs();

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setTrackAScholarFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setTrackAScholarFilePath(null));
    }

    @Test
    public void equals() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        GuiSettings differentGuiSettings = new GuiSettings(5, 6, 7, 8);
        Path path = Paths.get("data", "trackAScholar.json");
        Path differentPath = Paths.get("data", "addressBook.json");

        // same values -> returns true
        userPrefs.setGuiSettings(guiSettings);
        userPrefs.setTrackAScholarFilePath(path);
        UserPrefs userPrefsCopy = new UserPrefs(userPrefs);
        assertTrue(userPrefs.equals(userPrefsCopy));

        // same object -> returns true
        assertTrue(userPrefs.equals(userPrefs));

        // null -> returns false
        assertFalse(userPrefs.equals(null));

        // different types -> returns false
        assertFalse(userPrefs.equals(9));

        // different guiSettings -> returns false
        UserPrefs differentUserPrefs1 = new UserPrefs();
        differentUserPrefs1.setGuiSettings(differentGuiSettings);
        assertFalse(userPrefs.equals(differentUserPrefs1));

        // different trackAScholarFilePath -> returns false
        UserPrefs differentUserPrefs2 = new UserPrefs();
        differentUserPrefs2.setTrackAScholarFilePath(differentPath);
        assertFalse(userPrefs.equals(differentUserPrefs2));

    }
}
