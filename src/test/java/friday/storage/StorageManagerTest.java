package friday.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import friday.commons.core.GuiSettings;
import friday.model.Friday;
import friday.model.ReadOnlyFriday;
import friday.model.UserPrefs;
import friday.testutil.TypicalStudents;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonFridayStorage fridayStorage = new JsonFridayStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(fridayStorage, userPrefsStorage);
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
    public void fridayReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonFridayStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonFridayStorageTest} class.
         */
        Friday original = TypicalStudents.getTypicalFriday();
        storageManager.saveFriday(original);
        ReadOnlyFriday retrieved = storageManager.readFriday().get();
        assertEquals(original, new Friday(retrieved));
    }

    @Test
    public void getFridayFilePath() {
        assertNotNull(storageManager.getFridayFilePath());
    }

}
