package seedu.rc4hdb.logic.commands.storagecommands.filecommands;

import static seedu.rc4hdb.logic.commands.storagecommands.StorageCommandTestUtil.assertCommandFailure;
import static seedu.rc4hdb.logic.commands.storagecommands.StorageCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.logic.commands.storagecommands.StorageCommandTestUtil.assertFileDoesNotExist;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.rc4hdb.commons.util.FileUtil;
import seedu.rc4hdb.logic.StorageStub;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.storage.JsonResidentBookStorage;
import seedu.rc4hdb.storage.JsonUserPrefsStorage;
import seedu.rc4hdb.storage.ResidentBookStorage;
import seedu.rc4hdb.storage.Storage;
import seedu.rc4hdb.storage.StorageManager;
import seedu.rc4hdb.storage.UserPrefsStorage;

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

        Path filePath = getTempFilePath("AlreadyExists.json");
        FileDeleteCommand fileDeleteCommand = new FileDeleteCommand(filePath);
        FileUtil.createFile(filePath);

        assertCommandSuccess(fileDeleteCommand, storage, expectedMessage, expectedStorage);
        assertFileDoesNotExist(filePath);
    }

    @Test
    public void execute_fileDoesNotExist_throwsCommandException() {
        String expectedMessage = String.format(FileDeleteCommand.MESSAGE_FILE_NON_EXISTENT, "DoesNotExist.json");

        Path filePath = getTempFilePath("DoesNotExist.json");
        FileDeleteCommand fileDeleteCommand = new FileDeleteCommand(filePath);

        assertCommandFailure(fileDeleteCommand, storage, expectedMessage);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        String expectedMessage = String.format(FileDeleteCommand.MESSAGE_FAILED, "deleting");

        Path filePath = getTempFilePath("AlreadyExists.json");
        FileDeleteCommand fileDeleteCommand = new FileDeleteCommand(filePath);
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
    }
}
