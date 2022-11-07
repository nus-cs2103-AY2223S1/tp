package seedu.classify.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.classify.testutil.TypicalStudents.getTypicalStudentRecord;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.classify.commons.core.GuiSettings;
import seedu.classify.model.ReadOnlyStudentRecord;
import seedu.classify.model.StudentRecord;
import seedu.classify.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonStudentRecordStorage studentRecordStorage = new JsonStudentRecordStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(studentRecordStorage, userPrefsStorage);
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
    public void studentRecordReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonStudentrecordStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonStudentRecordStorageTest} class.
         */
        StudentRecord original = getTypicalStudentRecord();
        storageManager.saveStudentRecord(original);
        ReadOnlyStudentRecord retrieved = storageManager.readStudentRecord().get();
        assertEquals(original, new StudentRecord(retrieved));
    }

    @Test
    public void getStudentRecordFilePath() {
        assertNotNull(storageManager.getStudentRecordFilePath());
    }

}
