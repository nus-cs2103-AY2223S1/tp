package seedu.rc4hdb.logic.commands.filecommands;

import static seedu.rc4hdb.logic.commands.StorageCommandTestUtil.assertCommandFailure;
import static seedu.rc4hdb.logic.commands.StorageCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.logic.commands.StorageCommandTestUtil.assertDirExists;
import static seedu.rc4hdb.logic.commands.filecommands.FileCommand.MESSAGE_TRYING_TO_EXECUTE_ON_CURRENT_FILE;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.rc4hdb.commons.util.FileUtil;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.storage.DataStorageManager;
import seedu.rc4hdb.storage.Storage;
import seedu.rc4hdb.storage.StorageManager;
import seedu.rc4hdb.storage.StorageStub;
import seedu.rc4hdb.storage.userprefs.JsonUserPrefsStorage;
import seedu.rc4hdb.storage.userprefs.UserPrefsStorage;

/**
 * Unit tests for {@link FileCreateCommand}.
 */
public class FileCreateCommandTest {

    @TempDir
    public Path testFolder;

    private Storage storage;

    @BeforeEach
    public void setUp() {
        DataStorageManager dataStorageManager = new DataStorageManager(getTempFilePath("test"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("testPrefs.json"));
        storage = new StorageManager(dataStorageManager, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void execute_folderDoesNotExist_fileCreated() {
        DataStorageManager expectedDataStorage = new DataStorageManager(storage.getDataStorageFolderPath());
        UserPrefsStorage expectedUserPrefsStorage = new JsonUserPrefsStorage(storage.getUserPrefsFilePath());
        Storage expectedStorage = new StorageManager(expectedDataStorage, expectedUserPrefsStorage);

        String targetFolderName = "DoesNotExist";
        String expectedMessage = String.format(FileCreateCommand.MESSAGE_SUCCESS, targetFolderName);
        Path targetFolderPath = getTempFilePath(targetFolderName);

        FileCreateCommand fileCreateCommand = new FileCreateCommand(testFolder, targetFolderName);

        assertCommandSuccess(fileCreateCommand, storage, expectedMessage, expectedStorage);
        assertDirExists(targetFolderPath);
    }

    @Test
    public void execute_currentFolder_throwsCommandException() throws Exception {
        String targetFolderName = "test";
        Path targetFolderPath = getTempFilePath(targetFolderName);

        String expectedMessage = String.format(MESSAGE_TRYING_TO_EXECUTE_ON_CURRENT_FILE, targetFolderName);
        FileCreateCommand fileCreateCommand = new FileCreateCommand(testFolder, targetFolderName);
        FileUtil.createDirIfMissing(targetFolderPath);

        assertCommandFailure(fileCreateCommand, storage, expectedMessage);
    }

    @Test
    public void execute_folderAlreadyExists_throwsCommandException() throws IOException {
        String targetFolderName = "AlreadyExists";
        String expectedMessage = String.format(DataStorageManager.MESSAGE_FOLDER_ALREADY_EXIST, "AlreadyExists");
        Path targetFolderPath = getTempFilePath(targetFolderName);
        FileCreateCommand fileCreateCommand = new FileCreateCommand(testFolder, targetFolderName);
        FileUtil.createDirIfMissing(targetFolderPath);

        assertCommandFailure(fileCreateCommand, storage, expectedMessage);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        String expectedMessage = String.format(FileCreateCommand.MESSAGE_FAILED, "creating");

        String targetFolderName = "DoesNotExist";
        FileCreateCommand fileCreateCommand = new FileCreateCommand(testFolder, targetFolderName);
        storage = new StorageStubThrowsIoException();

        assertThrows(CommandException.class, expectedMessage, () -> fileCreateCommand.execute(storage));
    }

    /**
     * A storage stub class that throws an {@code IOException} when the
     * @see #createDataFolder(Path) method is invoked.
     */
    private static class StorageStubThrowsIoException extends StorageStub {
        public static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

        @Override
        public void createDataFolder(Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }

        @Override
        public Path getUserPrefsFilePath() {
            return null;
        }

        @Override
        public Path getDataStorageFolderPath() {
            return null;
        }
    }
}
