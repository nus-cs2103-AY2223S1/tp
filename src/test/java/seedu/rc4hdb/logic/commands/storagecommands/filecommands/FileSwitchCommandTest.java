package seedu.rc4hdb.logic.commands.storagecommands.filecommands;

import static seedu.rc4hdb.logic.commands.StorageModelCommandTestUtil.assertCommandFailure;
import static seedu.rc4hdb.logic.commands.StorageModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.commons.util.FileUtil;
import seedu.rc4hdb.commons.util.JsonUtil;
import seedu.rc4hdb.logic.StorageStub;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.storage.JsonResidentBookStorage;
import seedu.rc4hdb.storage.JsonSerializableResidentBook;
import seedu.rc4hdb.storage.JsonUserPrefsStorage;
import seedu.rc4hdb.storage.Storage;
import seedu.rc4hdb.storage.StorageManager;

/**
 * Unit tests for {@link FileSwitchCommand}.
 */
public class FileSwitchCommandTest {

    @TempDir
    public Path testFolder;

    private Storage storage;
    private Model model;

    @BeforeEach
    public void setUp() {
        JsonResidentBookStorage residentBookStorage = new JsonResidentBookStorage(getTempFilePath("test.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("testPrefs.json"));
        storage = new StorageManager(residentBookStorage, userPrefsStorage);
        model = new ModelManager();
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void execute_existingFile_switchFiles() throws Exception {
        Path target = getTempFilePath("target.json");
        Path userPrefPath = getTempFilePath("testPrefs.json");

        String expectedMessage = String.format(FileSwitchCommand.MESSAGE_SUCCESS, "target.json");
        Storage expectedStorage = new StorageManager(
                new JsonResidentBookStorage(target), new JsonUserPrefsStorage(userPrefPath));
        Model expectedModel = new ModelManager();
        expectedModel.setResidentBookFilePath(target);

        FileSwitchCommand fileSwitchCommand = new FileSwitchCommand(target);
        createEmptyJsonFile(target);

        assertCommandSuccess(fileSwitchCommand, storage, model, expectedMessage, expectedStorage, expectedModel);
    }

    @Test
    public void execute_currentFile_throwsCommandException() throws Exception {
        Path target = getTempFilePath("test.json");

        String expectedMessage = String.format(FileCommand.MESSAGE_TRYING_TO_EXECUTE_ON_CURRENT_FILE, "test.json");
        FileSwitchCommand fileSwitchCommand = new FileSwitchCommand(target);
        createEmptyJsonFile(target);

        assertCommandFailure(fileSwitchCommand, storage, model, expectedMessage);
    }

    @Test
    public void execute_targetFileDoesNotExist_throwsCommandException() {
        Path target = getTempFilePath("target.json");

        String expectedMessage = String.format(FileSwitchCommand.MESSAGE_FILE_DOES_NOT_EXIST, "target.json");

        FileSwitchCommand fileSwitchCommand = new FileSwitchCommand(target);

        assertCommandFailure(fileSwitchCommand, storage, model, expectedMessage);
    }

    @Test
    public void execute_invalidDataFileFormat_throwsCommandException() throws Exception {
        Path target = getTempFilePath("target.json");

        String expectedMessage = String.format(FileSwitchCommand.MESSAGE_INVALID_FILE_DATA, "target.json");

        FileSwitchCommand fileSwitchCommand = new FileSwitchCommand(target);
        storage = new StorageStubThrowsDataConversionException();
        createEmptyJsonFile(target);

        assertThrows(CommandException.class, expectedMessage, () -> fileSwitchCommand.execute(storage, model));
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() throws Exception {
        Path target = getTempFilePath("target.json");

        String expectedMessage = String.format(FileSwitchCommand.MESSAGE_FAILED, "switching");

        FileSwitchCommand fileSwitchCommand = new FileSwitchCommand(target);
        storage = new StorageStubThrowsIoException();
        createEmptyJsonFile(target);

        assertThrows(CommandException.class, expectedMessage, () -> fileSwitchCommand.execute(storage, model));
    }

    /**
     * Creates an empty JSON file.
     */
    private void createEmptyJsonFile(Path filePath) throws IOException {
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableResidentBook(new ResidentBook()), filePath);
    }

    /**
     * A storage stub class that throws an {@code IOException} when the
     * @see #deleteResidentBookFile(Path) method is invoked.
     */
    private static class StorageStubThrowsDataConversionException extends StorageStub {
        public static final DataConversionException DUMMY_IO_EXCEPTION = new DataConversionException(new IOException());

        @Override
        public Optional<ReadOnlyResidentBook> readResidentBook(Path filePath) throws DataConversionException {
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

    /**
     * A storage stub class that throws an {@code IOException} when the
     * @see #deleteResidentBookFile(Path) method is invoked.
     */
    private static class StorageStubThrowsIoException extends StorageStub {
        public static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

        @Override
        public Optional<ReadOnlyResidentBook> readResidentBook(Path filePath) throws IOException {
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
