package seedu.boba.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.boba.commons.core.GuiSettings;
import seedu.boba.model.BobaBot;
import seedu.boba.model.ReadOnlyBobaBot;
import seedu.boba.model.UserPrefs;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.boba.testutil.TypicalCustomers.getTypicalBobaBot;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonBobaBotStorage bobaBotStorage = new JsonBobaBotStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(bobaBotStorage, userPrefsStorage);
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
    public void bobaBotReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonBobaBotStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonBobaBotStorageTest} class.
         */
        BobaBot original = getTypicalBobaBot();
        storageManager.saveBobaBot(original);
        ReadOnlyBobaBot retrieved = storageManager.readBobaBot().get();
        assertEquals(original, new BobaBot(retrieved));
    }

    @Test
    public void getBobaBotFilePath() {
        assertNotNull(storageManager.getBobaBotFilePath());
    }

}
