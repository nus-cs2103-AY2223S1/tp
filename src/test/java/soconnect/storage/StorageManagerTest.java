package soconnect.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import soconnect.commons.core.GuiSettings;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.SoConnect;
import soconnect.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonSoConnectStorage soConnectStorage = new JsonSoConnectStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(soConnectStorage, userPrefsStorage);
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
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6,
                "ADDRESS>TAGS>PHONE>EMAIL", "NONE"));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void soConnectReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonSoConnectStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonSoConnectStorageTest} class.
         */
        SoConnect original = getTypicalSoConnect();
        storageManager.saveSoConnect(original);
        ReadOnlySoConnect retrieved = storageManager.readSoConnect().get();
        assertEquals(original, new SoConnect(retrieved));
    }

    @Test
    public void getSoConnectFilePath() {
        assertNotNull(storageManager.getSoConnectFilePath());
    }

}
