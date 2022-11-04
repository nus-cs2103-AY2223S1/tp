package seedu.waddle.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.waddle.testutil.TypicalItineraries.getTypicalWaddle;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.waddle.commons.core.GuiSettings;
import seedu.waddle.model.ReadOnlyWaddle;
import seedu.waddle.model.UserPrefs;
import seedu.waddle.model.Waddle;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonWaddleStorage waddleStorage = new JsonWaddleStorage(getTempFilePath("waddle"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(waddleStorage, userPrefsStorage);
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
    public void waddleReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonWaddleStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonWaddleStorageTest} class.
         */
        Waddle original = getTypicalWaddle();
        storageManager.saveWaddle(original);
        ReadOnlyWaddle retrieved = storageManager.readWaddle().get();
        assertEquals(original, new Waddle(retrieved));
    }

    @Test
    public void getWaddleFilePath() {
        assertNotNull(storageManager.getWaddleFilePath());
    }

}
