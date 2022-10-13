package seedu.phu.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.phu.testutil.TypicalInternships.getTypicalInternshipBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.phu.commons.core.GuiSettings;
import seedu.phu.model.InternshipBook;
import seedu.phu.model.ReadOnlyInternshipBook;
import seedu.phu.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonInternshipBookStorage internshipBookStorage = new JsonInternshipBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(internshipBookStorage, userPrefsStorage);
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
    public void internshipBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonInternshipBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonInternshipBookStorageTest} class.
         */
        InternshipBook original = getTypicalInternshipBook();
        storageManager.saveInternshipBook(original);
        ReadOnlyInternshipBook retrieved = storageManager.readInternshipBook().get();
        assertEquals(original, new InternshipBook(retrieved));
    }

    @Test
    public void getInternshipBookFilePath() {
        assertNotNull(storageManager.getInternshipBookFilePath());
    }

}
