package seedu.rc4hdb.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.rc4hdb.commons.util.FileUtil;
import seedu.rc4hdb.storage.residentbook.JsonResidentBookStorage;
import seedu.rc4hdb.storage.residentbook.ResidentBookStorage;
import seedu.rc4hdb.storage.venuebook.JsonVenueBookStorage;
import seedu.rc4hdb.storage.venuebook.VenueBookStorage;
import seedu.rc4hdb.ui.ObservableItem;

public class DataStorageManagerTest {

    @TempDir
    public Path testFolder;

    private DataStorageManager dataStorageManager;
    private ResidentBookStorage residentBookStorage;
    private VenueBookStorage venueBookStorage;

    @BeforeEach
    public void setUp() {
        dataStorageManager = new DataStorageManager(getTempFilePath("ab"));
        residentBookStorage = new JsonResidentBookStorage();
        venueBookStorage = new JsonVenueBookStorage();
    }

    private Path getTempFilePath(String filename) {
        return testFolder.resolve(filename);
    }

    @Test
    public void getDataStorageFolderPath_success() {
        Path original = getTempFilePath("ab");
        Path retrieved = dataStorageManager.getDataStorageFolderPath();
        assertEquals(original, retrieved);
    }

    @Test
    public void setDataStoragePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> dataStorageManager.setDataStorageFolderPath(null));
    }

    @Test
    public void setDataStoragePath_validFilePath_filePathSet() {
        Path expectedPath = Path.of("SomeFile");
        dataStorageManager.setDataStorageFolderPath(expectedPath);
        assertEquals(expectedPath, dataStorageManager.getDataStorageFolderPath());
    }

    @Test
    public void setDataStoragePath_getObservableFolderPath() {
        Path expectedPath = Path.of("SomeFile");
        dataStorageManager.setDataStorageFolderPath(expectedPath);
        assertEquals(new ObservableItem<>(expectedPath), dataStorageManager.getObservableFolderPath());
    }

    @Test
    public void deleteDataFolder_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> dataStorageManager.deleteDataFolder(null));
    }

    @Test
    public void deleteDataFolder_existingFolder_folderDeleted() throws Exception {
        Path folderPath = testFolder.resolve("ToBeDeleted");
        FileUtil.createDirIfMissing(folderPath);
        dataStorageManager.deleteDataFolder(folderPath);
        assertFalse(FileUtil.isFolderExists(folderPath));
    }

    @Test
    public void deleteDataFolder_folderDoesNotExist_throwsNoSuchFileException() {
        Path toBeDeleted = testFolder.resolve("ToBeDeleted");
        assertThrows(NoSuchFileException.class, () -> dataStorageManager.deleteDataFolder(toBeDeleted));
    }

    @Test
    public void createDataFolder_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> dataStorageManager.createDataFolder(null));
    }

    @Test
    public void createDataFolder_folderDoesNotExist_folderCreated() throws Exception {
        Path toBeCreated = testFolder.resolve("ToBeCreated");
        assertFalse(FileUtil.isFolderExists(toBeCreated));
        dataStorageManager.createDataFolder(toBeCreated);
        assertTrue(FileUtil.isFolderExists(toBeCreated));
    }

    @Test
    public void createDataFolder_folderAlreadyExist_throwsFileAlreadyExistException() throws Exception {
        Path toBeCreated = testFolder.resolve("ToBeCreated");
        FileUtil.createDirIfMissing(toBeCreated);
        assertThrows(FileAlreadyExistsException.class, () -> dataStorageManager.createDataFolder(toBeCreated));
    }

}
