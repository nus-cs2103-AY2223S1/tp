package seedu.rc4hdb.logic.commands.filecommands.jsonfilecommands;

import static seedu.rc4hdb.logic.commands.StorageCommandTestUtil.assertCommandFailure;
import static seedu.rc4hdb.logic.commands.StorageCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.logic.commands.StorageCommandTestUtil.assertFileDoesNotExist;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.rc4hdb.commons.util.FileUtil;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.commands.filecommands.FileCommand;
import seedu.rc4hdb.logic.commands.filecommands.FileDeleteCommand;
import seedu.rc4hdb.storage.userprefs.JsonUserPrefsStorage;
import seedu.rc4hdb.storage.Storage;
import seedu.rc4hdb.storage.StorageManager;
import seedu.rc4hdb.storage.StorageStub;
import seedu.rc4hdb.storage.userprefs.UserPrefsStorage;
import seedu.rc4hdb.storage.residentbook.JsonResidentBookStorage;
import seedu.rc4hdb.storage.residentbook.ResidentBookStorage;

/**
 * Unit tests for {@link FileDeleteCommand}.
 */
public class FileDeleteCommandTest {

    @TempDir
    public Path testFolder;

    private Storage storage;

    @BeforeEach
    public void setUp() {
        JsonResidentBookStorage residentBookStorage = new JsonResidentBookStorage(getTempFilePath("test.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("testPrefs.json"));
        storage = new StorageManager(residentBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void execute_fileExists_fileDeleted() throws Exception {
        ResidentBookStorage expectedResidentBookStorage =
                new JsonResidentBookStorage(storage.getResidentBookFilePath());
        UserPrefsStorage expectedUserPrefsStorage = new JsonUserPrefsStorage(storage.getUserPrefsFilePath());
        Storage expectedStorage = new StorageManager(expectedResidentBookStorage, expectedUserPrefsStorage);
        String expectedMessage = String.format(FileDeleteCommand.MESSAGE_SUCCESS, "AlreadyExists.json");

        Path targetFilePath = getTempFilePath("AlreadyExists.json");
        String targetFileName = "AlreadyExists";
        FileDeleteCommand fileDeleteCommand = new FileDeleteCommand(testFolder, targetFileName);
        FileUtil.createFile(targetFilePath);

        assertCommandSuccess(fileDeleteCommand, storage, expectedMessage, expectedStorage);
        assertFileDoesNotExist(targetFilePath);
    }

    @Test
    public void execute_currentFile_throwsCommandException() throws Exception {
        Path target = getTempFilePath("test.json");
        String targetFileName = "test";

        String expectedMessage = String.format(FileCommand.MESSAGE_TRYING_TO_EXECUTE_ON_CURRENT_FILE, "test.json");
        FileDeleteCommand fileDeleteCommand = new FileDeleteCommand(testFolder, targetFileName);
        FileUtil.createIfMissing(target);

        assertCommandFailure(fileDeleteCommand, storage, expectedMessage);
    }

    @Test
    public void execute_fileDoesNotExist_throwsCommandException() {
        String expectedMessage = String.format(FileDeleteCommand.MESSAGE_FILE_NON_EXISTENT, "DoesNotExist.json");

        String targetFileName = "DoesNotExist";
        FileDeleteCommand fileDeleteCommand = new FileDeleteCommand(testFolder, targetFileName);

        assertCommandFailure(fileDeleteCommand, storage, expectedMessage);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        String expectedMessage = String.format(FileDeleteCommand.MESSAGE_FAILED, "deleting");

        String targetFileName = "AlreadyExists";
        FileDeleteCommand fileDeleteCommand = new FileDeleteCommand(testFolder, targetFileName);
        storage = new StorageStubThrowsIoException();

        assertThrows(CommandException.class, expectedMessage, () -> fileDeleteCommand.execute(storage));
    }

    /**
     * A storage stub class that throws an {@code IOException} when the
     * @see #deleteResidentBookFile(Path) method is invoked.
     */
    private static class StorageStubThrowsIoException extends StorageStub {
        public static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

        @Override
        public void deleteResidentBookFile(Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }

        @Override
        public Path getResidentBookFilePath() {
            return null;
        }

        @Override
        public Path getUserPrefsFilePath() {
            return null;
        }
    }
}
