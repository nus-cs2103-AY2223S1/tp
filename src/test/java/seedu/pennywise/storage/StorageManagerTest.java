package seedu.pennywise.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.pennywise.testutil.TypicalEntry.getTypicalPennyWise;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.pennywise.commons.core.GuiSettings;
import seedu.pennywise.model.PennyWise;
import seedu.pennywise.model.ReadOnlyPennyWise;
import seedu.pennywise.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonPennyWiseStorage pennyWiseStorage = new JsonPennyWiseStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(pennyWiseStorage, userPrefsStorage);
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
    public void pennyWiseReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonPennyWiseStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonPennyWiseStorageTest} class.
         */
        PennyWise original = getTypicalPennyWise();
        storageManager.savePennyWise(original);
        ReadOnlyPennyWise retrieved = storageManager.readPennyWise().get();
        assertEquals(original, new PennyWise(retrieved));
    }

    @Test
    public void getPennyWiseFilePath() {
        assertNotNull(storageManager.getPennyWiseFilePath());
    }

}
