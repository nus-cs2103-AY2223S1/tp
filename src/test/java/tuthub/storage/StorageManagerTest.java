package tuthub.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import tuthub.commons.core.GuiSettings;
import tuthub.model.ReadOnlyTuthub;
import tuthub.model.Tuthub;
import tuthub.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTuthubStorage tuthubStorage = new JsonTuthubStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(tuthubStorage, userPrefsStorage);
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
    public void tuthubReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTuthubStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonTuthubStorageTest} class.
         */
        Tuthub original = getTypicalTuthub();
        storageManager.saveTuthub(original);
        ReadOnlyTuthub retrieved = storageManager.readTuthub().get();
        assertEquals(original, new Tuthub(retrieved));
    }

    @Test
    public void getTuthubFilePath() {
        assertNotNull(storageManager.getTuthubFilePath());
    }

}
