package seedu.workbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.workbook.testutil.TypicalInternships.getTypicalWorkBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.workbook.commons.core.GuiSettings;
import seedu.workbook.model.ReadOnlyWorkBook;
import seedu.workbook.model.UserPrefs;
import seedu.workbook.model.WorkBook;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonWorkBookStorage workBookStorage = new JsonWorkBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(workBookStorage, userPrefsStorage);
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
    public void workBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonWorkBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonWorkBookStorageTest} class.
         */
        WorkBook original = getTypicalWorkBook();
        storageManager.saveWorkBook(original);
        ReadOnlyWorkBook retrieved = storageManager.readWorkBook().get();
        assertEquals(original, new WorkBook(retrieved));
    }

    @Test
    public void getWorkBookFilePath() {
        assertNotNull(storageManager.getWorkBookFilePath());
    }

}
