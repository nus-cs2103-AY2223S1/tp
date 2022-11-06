package coydir.storage;

import static coydir.testutil.TypicalPersons.getTypicalDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import coydir.commons.core.GuiSettings;
import coydir.model.Database;
import coydir.model.ReadOnlyDatabase;
import coydir.model.UserPrefs;
import coydir.testutil.TestUtil;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonDatabaseStorage databaseStorage = new JsonDatabaseStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(databaseStorage, userPrefsStorage);
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
    public void databaseReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonDatabaseStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonDatabaseStorageTest} class.
         */
        Database original = getTypicalDatabase();
        int numOfPersons = original.getPersonList().size();
        TestUtil.setMaxEmployeeId(numOfPersons + 1);
        storageManager.saveDatabase(original);
        TestUtil.restartEmployeeId(1);
        ReadOnlyDatabase retrieved = storageManager.readDatabase().get();
        assertEquals(original, new Database(retrieved));
    }

    @Test
    public void getDatabaseFilePath() {
        assertNotNull(storageManager.getDatabaseFilePath());
    }

}
