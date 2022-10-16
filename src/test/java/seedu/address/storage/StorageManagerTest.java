package seedu.uninurse.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.uninurse.testutil.TypicalPersons.getTypicalUninurseBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.uninurse.commons.core.GuiSettings;
import seedu.uninurse.model.UninurseBook;
import seedu.uninurse.model.ReadOnlyUninurseBook;
import seedu.uninurse.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonUninurseBookStorage uninurseBookStorage = new JsonUninurseBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(uninurseBookStorage, userPrefsStorage);
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
    public void uninurseBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUninurseBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUninurseBookStorageTest} class.
         */
        UninurseBook original = getTypicalUninurseBook();
        storageManager.saveUninurseBook(original);
        ReadOnlyUninurseBook retrieved = storageManager.readUninurseBook().get();
        assertEquals(original, new UninurseBook(retrieved));
    }

    @Test
    public void getUninurseBookFilePath() {
        assertNotNull(storageManager.getUninurseBookFilePath());
    }

}
