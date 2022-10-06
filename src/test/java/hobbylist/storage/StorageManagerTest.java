package hobbylist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import hobbylist.commons.core.GuiSettings;
import hobbylist.model.HobbyList;
import hobbylist.model.ReadOnlyHobbyList;
import hobbylist.model.UserPrefs;
import hobbylist.testutil.TypicalActivities;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonHobbyListStorage hobbyListStorage = new JsonHobbyListStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(hobbyListStorage, userPrefsStorage);
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
    public void hobbyListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonHobbyListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonHobbyListStorageTest} class.
         */
        HobbyList original = TypicalActivities.getTypicalHobbyList();
        storageManager.saveHobbyList(original);
        ReadOnlyHobbyList retrieved = storageManager.readHobbyList().get();
        assertEquals(original, new HobbyList(retrieved));
    }

    @Test
    public void getHobbyListFilePath() {
        assertNotNull(storageManager.getHobbyListFilePath());
    }

}
