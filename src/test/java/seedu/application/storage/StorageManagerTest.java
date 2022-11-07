package seedu.application.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.application.commons.core.GuiSettings;
import seedu.application.model.ApplicationBook;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager applicationStorageManager;

    @BeforeEach
    public void setUp() {
        JsonApplicationBookStorage applicationBookStorage = new JsonApplicationBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        applicationStorageManager = new StorageManager(applicationBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        applicationStorageManager.saveUserPrefs(original);
        UserPrefs retrieved = applicationStorageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void applicationBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonApplicationBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonApplicationBookStorageTest} class.
         */
        ApplicationBook original = getTypicalApplicationBook();
        applicationStorageManager.saveApplicationBook(original);
        ReadOnlyApplicationBook retrieved = applicationStorageManager.readApplicationBook().get();
        assertEquals(original, new ApplicationBook(retrieved));
    }

    @Test
    public void getApplicationBookFilePath() {
        assertNotNull(applicationStorageManager.getApplicationBookFilePath());
    }

}
