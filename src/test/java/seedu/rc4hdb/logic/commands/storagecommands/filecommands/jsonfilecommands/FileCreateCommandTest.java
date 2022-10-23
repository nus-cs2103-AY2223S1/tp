package seedu.rc4hdb.logic.commands.storagecommands.filecommands.jsonfilecommands;

import static seedu.rc4hdb.logic.commands.storagecommands.StorageCommandTestUtil.assertCommandFailure;
import static seedu.rc4hdb.logic.commands.storagecommands.StorageCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.logic.commands.storagecommands.StorageCommandTestUtil.assertFileExists;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.rc4hdb.commons.util.FileUtil;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.commands.storagecommands.filecommands.FileCommand;
import seedu.rc4hdb.storage.JsonResidentBookStorage;
import seedu.rc4hdb.storage.JsonUserPrefsStorage;
import seedu.rc4hdb.storage.ResidentBookStorage;
import seedu.rc4hdb.storage.Storage;
import seedu.rc4hdb.storage.StorageManager;
import seedu.rc4hdb.storage.StorageStub;
import seedu.rc4hdb.storage.UserPrefsStorage;

/**
 * Unit tests for {@link FileCreateCommand}.
 */
public class FileCreateCommandTest {

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
    public void execute_fileDoesNotExist_fileCreated() {
        ResidentBookStorage expectedResidentBookStorage =
                new JsonResidentBookStorage(storage.getResidentBookFilePath());
        UserPrefsStorage expectedUserPrefsStorage = new JsonUserPrefsStorage(storage.getUserPrefsFilePath());
        Storage expectedStorage = new StorageManager(expectedResidentBookStorage, expectedUserPrefsStorage);
        String expectedMessage = String.format(FileCreateCommand.MESSAGE_SUCCESS, "DoesNotExist.json");

        Path targetFilePath = getTempFilePath("DoesNotExist.json");
        String targetFileName = "DoesNotExist";
        FileCreateCommand fileCreateCommand = new FileCreateCommand(testFolder, targetFileName);

        assertCommandSuccess(fileCreateCommand, storage, expectedMessage, expectedStorage);
        assertFileExists(targetFilePath);
    }

    @Test
    public void execute_currentFile_throwsCommandException() throws Exception {
        Path target = getTempFilePath("test.json");
        String targetFileName = "test";

        String expectedMessage = String.format(FileCommand.MESSAGE_TRYING_TO_EXECUTE_ON_CURRENT_FILE, "test.json");
        FileCreateCommand fileCreateCommand = new FileCreateCommand(testFolder, targetFileName);
        FileUtil.createIfMissing(target);

        assertCommandFailure(fileCreateCommand, storage, expectedMessage);
    }

    @Test
    public void execute_fileAlreadyExists_throwsCommandException() throws IOException {
        String expectedMessage = String.format(FileCreateCommand.MESSAGE_FILE_EXISTS, "AlreadyExists.json");

        Path filePath = getTempFilePath("AlreadyExists.json");
        String targetFileName = "AlreadyExists";
        FileCreateCommand fileCreateCommand = new FileCreateCommand(testFolder, targetFileName);
        FileUtil.createFile(filePath);

        assertCommandFailure(fileCreateCommand, storage, expectedMessage);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        String expectedMessage = String.format(FileCreateCommand.MESSAGE_FAILED, "creating");

        String targetFileName = "DoesNotExist";
        FileCreateCommand fileCreateCommand = new FileCreateCommand(testFolder, targetFileName);
        storage = new StorageStubThrowsIoException();

        assertThrows(CommandException.class, expectedMessage, () -> fileCreateCommand.execute(storage));
    }

    /**
     * A storage stub class that throws an {@code IOException} when the
     * @see #createResidentBookFile(Path) method is invoked.
     */
    private static class StorageStubThrowsIoException extends StorageStub {
        public static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

        @Override
        public void createResidentBookFile(Path filePath) throws IOException {
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
