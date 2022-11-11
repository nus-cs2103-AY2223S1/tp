package seedu.studmap.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.studmap.testutil.TypicalStudents.getTypicalStudMap;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.studmap.commons.core.GuiSettings;
import seedu.studmap.model.ReadOnlyStudMap;
import seedu.studmap.model.StudMap;
import seedu.studmap.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonStudMapStorage studMapStorage = new JsonStudMapStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(studMapStorage, userPrefsStorage);
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
    public void studMapReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonStudMapStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonStudMapStorageTest} class.
         */
        StudMap original = getTypicalStudMap();
        storageManager.saveStudMap(original);
        ReadOnlyStudMap retrieved = storageManager.readStudMap().get();
        assertEquals(original, new StudMap(retrieved));
    }

    @Test
    public void getStudMapFilePath() {
        assertNotNull(storageManager.getStudMapFilePath());
    }

}
