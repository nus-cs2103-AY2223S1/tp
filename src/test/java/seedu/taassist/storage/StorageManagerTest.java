package seedu.taassist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.taassist.testutil.TypicalStudents.getTypicalTaAssist;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.taassist.commons.core.GuiSettings;
import seedu.taassist.commons.util.FileUtil;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.TaAssist;
import seedu.taassist.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTaAssistStorage taAssistStorage = new JsonTaAssistStorage(getTempFilePath("tassist.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("preferences.json"));
        storageManager = new StorageManager(taAssistStorage, userPrefsStorage);
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
    public void taAssistReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTaAssistStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonTaAssistStorageTest} class.
         */
        TaAssist original = getTypicalTaAssist();
        storageManager.saveTaAssist(original);
        ReadOnlyTaAssist retrieved = storageManager.readTaAssist().get();
        assertEquals(original, new TaAssist(retrieved));
    }

    @Test
    public void getTaAssistFilePath() {
        assertNotNull(storageManager.getTaAssistFilePath());
    }

    @Test
    public void backupFile_backUpDoesntExist_createNewBackup() throws IOException {
        String content = "test content";
        Path tempFilePath = getTempFilePath("temp.json");
        FileUtil.writeToFile(tempFilePath, content);
        storageManager.backupFile(tempFilePath);
        Path backupFilePath = getTempFilePath("temp.json.bak");
        assertEquals(FileUtil.readFromFile(backupFilePath), content);
    }

    @Test
    public void backupFile_backUpExists_overwriteBackup() throws IOException {
        String content = "test content";
        Path tempFilePath = getTempFilePath("temp.json");
        FileUtil.writeToFile(tempFilePath, content);
        storageManager.backupFile(tempFilePath);
        String newContent = "new test content";
        FileUtil.writeToFile(tempFilePath, newContent);
        storageManager.backupFile(tempFilePath);
        Path backupFilePath = getTempFilePath("temp.json.bak");
        assertEquals(FileUtil.readFromFile(backupFilePath), newContent);
    }

}
