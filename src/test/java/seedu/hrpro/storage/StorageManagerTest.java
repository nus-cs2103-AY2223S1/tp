package seedu.hrpro.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.hrpro.testutil.TypicalHRPro.getTypicalHRPro;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.hrpro.commons.core.GuiSettings;
import seedu.hrpro.model.HRPro;
import seedu.hrpro.model.ReadOnlyHRPro;
import seedu.hrpro.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonHRProStorage hrProStorage = new JsonHRProStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(hrProStorage, userPrefsStorage);
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
    public void hrProReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonHRProStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonHRProStorageTest} class.
         */
        HRPro original = getTypicalHRPro();
        storageManager.saveHRPro(original);
        ReadOnlyHRPro retrieved = storageManager.readHRPro().get();
        assertEquals(original, new HRPro(retrieved));
    }

    @Test
    public void getHRProFilePath() {
        assertNotNull(storageManager.getHRProFilePath());
    }

}
