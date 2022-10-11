package bookface.storage;

import static bookface.testutil.TypicalPersons.getTypicalBookFaceData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import bookface.commons.core.GuiSettings;
import bookface.model.BookFace;
import bookface.model.ReadOnlyBookFace;
import bookface.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonBookFaceStorage bookFaceStorage = new JsonBookFaceStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(bookFaceStorage, userPrefsStorage);
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
    public void bookFaceReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonBookFaceStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonBookFaceStorageTest} class.
         */
        BookFace original = getTypicalBookFaceData();
        storageManager.saveBookFace(original);
        ReadOnlyBookFace retrieved = storageManager.readBookFace().get();
        assertEquals(original, new BookFace(retrieved));
    }

    @Test
    public void getBookFaceFilePath() {
        assertNotNull(storageManager.getBookFaceFilePath());
    }

}
