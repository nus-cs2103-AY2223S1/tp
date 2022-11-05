package seedu.realtime.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.realtime.testutil.TypicalPersons.getTypicalRealTime;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.realtime.commons.core.GuiSettings;
import seedu.realtime.model.ReadOnlyRealTime;
import seedu.realtime.model.RealTime;
import seedu.realtime.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonRealTimeStorage realTimeStorage = new JsonRealTimeStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(realTimeStorage, userPrefsStorage);
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
    public void realTimeReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonRealTimeStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonRealTimeStorageTest} class.
         */
        RealTime original = getTypicalRealTime();
        storageManager.saveRealTime(original);
        ReadOnlyRealTime retrieved = storageManager.readRealTime().get();
        assertEquals(original, new RealTime(retrieved));
    }

    @Test
    public void getRealTimeFilePath() {
        assertNotNull(storageManager.getRealTimeFilePath());
    }

}
