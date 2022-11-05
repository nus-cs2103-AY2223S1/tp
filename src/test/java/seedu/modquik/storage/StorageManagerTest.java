package seedu.modquik.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.modquik.testutil.TypicalPersons.getTypicalModQuik;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.modquik.commons.core.GuiSettings;
import seedu.modquik.model.ModQuik;
import seedu.modquik.model.ReadOnlyModQuik;
import seedu.modquik.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonModQuikStorage addressBookStorage = new JsonModQuikStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
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
    public void modQuikReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonModQuikStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonModQuikStorageTest} class.
         */
        ModQuik original = getTypicalModQuik();
        storageManager.saveModQuik(original);
        ReadOnlyModQuik retrieved = storageManager.readModQuik().get();
        assertEquals(original, new ModQuik(retrieved));
    }

    @Test
    public void getModQuikFilePath() {
        assertNotNull(storageManager.getModQuikFilePath());
    }

}
