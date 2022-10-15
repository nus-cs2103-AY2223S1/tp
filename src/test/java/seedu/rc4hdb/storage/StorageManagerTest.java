package seedu.rc4hdb.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.rc4hdb.commons.core.GuiSettings;
import seedu.rc4hdb.commons.util.FileUtil;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.UserPrefs;

/**
 * Unit test for {@link StorageManager}.
 */
public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonResidentBookStorage residentBookStorage = new JsonResidentBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(residentBookStorage, userPrefsStorage);
    }

    //=================== User Prefs Storage Tests ==================================

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void getUserPrefsFilePath() {
        assertNotNull(storageManager.getUserPrefsFilePath());
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

    //=================== Resident Book Storage Tests ==================================

    @Test
    public void residentBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonResidentBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonResidentBookStorageTest} class.
         */
        ResidentBook original = getTypicalResidentBook();
        storageManager.saveResidentBook(original);
        ReadOnlyResidentBook retrieved = storageManager.readResidentBook().get();
        assertEquals(original, new ResidentBook(retrieved));
    }

    @Test
    public void getResidentBookFilePath() {
        assertNotNull(storageManager.getResidentBookFilePath());
    }

    @Test
    public void setResidentBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> storageManager.setResidentBookFilePath(null));
    }

    @Test
    public void setResidentBookFilePath_validFilePath_filePathSet() {
        Path expectedPath = Path.of("SomeFile.json");
        storageManager.setResidentBookFilePath(expectedPath);
        assertEquals(expectedPath, storageManager.getResidentBookFilePath());
    }

    @Test
    public void deleteResidentBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> storageManager.deleteResidentBookFile(null));
    }

    @Test
    public void deleteResidentBook_existingFile_fileDeleted() throws Exception {
        Path toBeDeleted = testFolder.resolve("ToBeDeleted.json");
        FileUtil.createIfMissing(toBeDeleted);
        storageManager.deleteResidentBookFile(toBeDeleted);
        assertFalse(FileUtil.isFileExists(toBeDeleted));
    }

    @Test
    public void deleteResidentBook_fileDoesNotExist_throwsNoSuchFileException() {
        Path toBeDeleted = testFolder.resolve("ToBeDeleted.json");
        assertThrows(NoSuchFileException.class, () -> storageManager.deleteResidentBookFile(toBeDeleted));
    }

    @Test
    public void createResidentBookFile_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> storageManager.createResidentBookFile(null));
    }

    @Test
    public void createResidentBookFile_fileDoesNotExist_fileCreated() throws Exception {
        Path toBeCreated = testFolder.resolve("ToBeCreated.json");
        storageManager.createResidentBookFile(toBeCreated);
        assertTrue(FileUtil.isFileExists(toBeCreated));
    }

    @Test
    public void createResidentBookFile_fileAlreadyExist_throwsFileAlreadyExistException() throws Exception {
        Path toBeCreated = testFolder.resolve("ToBeCreated.json");
        storageManager.createResidentBookFile(toBeCreated);
        assertThrows(FileAlreadyExistsException.class, () -> storageManager.createResidentBookFile(toBeCreated));
    }

    @Test
    public void equals() {
        JsonResidentBookStorage residentBookStorage = new JsonResidentBookStorage(getTempFilePath("ab"));
        JsonResidentBookStorage otherResidentBookStorage = new JsonResidentBookStorage(getTempFilePath("bc"));

        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonUserPrefsStorage otherUserPrefStorage = new JsonUserPrefsStorage(getTempFilePath("otherPrefs"));

        StorageManager original = new StorageManager(residentBookStorage, userPrefsStorage);
        StorageManager bothSame = new StorageManager(residentBookStorage, userPrefsStorage);
        StorageManager diffResidentBookStorage = new StorageManager(otherResidentBookStorage, userPrefsStorage);
        StorageManager diffUserPrefsStorage = new StorageManager(residentBookStorage, otherUserPrefStorage);

        // Same object
        assertEquals(original, original);

        // Same user pref storage and resident book storage
        assertEquals(original, bothSame);

        // null storage manager
        assertNotEquals(original, null);

        // Different resident book storage
        assertNotEquals(original, diffResidentBookStorage);

        // Different user prefs storage
        assertNotEquals(original, diffUserPrefsStorage);

        // Both different
        assertNotEquals(diffResidentBookStorage, diffUserPrefsStorage);
    }
}
