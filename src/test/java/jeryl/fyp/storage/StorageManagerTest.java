package jeryl.fyp.storage;

import static jeryl.fyp.testutil.TypicalStudents.getTypicalFypManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import jeryl.fyp.commons.core.GuiSettings;
import jeryl.fyp.model.FypManager;
import jeryl.fyp.model.ReadOnlyFypManager;
import jeryl.fyp.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonFypManagerStorage fypManagerStorage = new JsonFypManagerStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(fypManagerStorage, userPrefsStorage);
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
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void fypManagerReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonFypManagerStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonFypManagerStorageTest} class.
         */
        FypManager original = getTypicalFypManager();
        storageManager.saveFypManager(original);
        ReadOnlyFypManager retrieved = storageManager.readFypManager().get();
        assertEquals(original, new FypManager(retrieved));
    }

    @Test
    public void getFypManagerFilePath() {
        assertNotNull(storageManager.getFypManagerFilePath());
    }

}
