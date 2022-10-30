package seedu.rc4hdb.logic.commands.filecommands;

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
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ReadOnlyVenueBook;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.VenueBook;
import seedu.rc4hdb.storage.DataStorageManager;
import seedu.rc4hdb.storage.Storage;
import seedu.rc4hdb.storage.StorageManager;
import seedu.rc4hdb.storage.StorageStub;
import seedu.rc4hdb.storage.residentbook.JsonResidentBookStorage;
import seedu.rc4hdb.storage.residentbook.JsonSerializableResidentBook;
import seedu.rc4hdb.storage.userprefs.JsonUserPrefsStorage;
import seedu.rc4hdb.storage.venuebook.JsonSerializableVenueBook;
import seedu.rc4hdb.storage.venuebook.JsonVenueBookStorage;

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
        DataStorageManager dataStorage = new DataStorageManager(getTempFilePath("test"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("testPrefs.json"));
        storage = new StorageManager(dataStorage, userPrefsStorage);
        model = new ModelManager();
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void execute_existingFolder_switchFolder() throws Exception {
        String targetFolderName = "target";
        Path targetFolderPath = getTempFilePath(targetFolderName);
        Path userPrefPath = getTempFilePath("testPrefs.json");

        String expectedMessage = String.format(FileSwitchCommand.MESSAGE_SUCCESS, targetFolderName);
        Storage expectedStorage = new StorageManager(
                new DataStorageManager(targetFolderPath), new JsonUserPrefsStorage(userPrefPath));
        Model expectedModel = new ModelManager();
        expectedModel.setUserPrefDataFilePath(targetFolderPath);

        FileSwitchCommand fileSwitchCommand = new FileSwitchCommand(testFolder, targetFolderName);
        createBothEmptyDataFile(targetFolderPath);

        assertCommandSuccess(fileSwitchCommand, storage, model, expectedMessage, expectedStorage, expectedModel);
    }

    @Test
    public void execute_currentFolder_throwsCommandException() throws Exception {
        String targetFolderName = "test";
        Path targetFolderPath = getTempFilePath(targetFolderName);

        String expectedMessage = String.format(FileCommand.MESSAGE_TRYING_TO_EXECUTE_ON_CURRENT_FILE, targetFolderName);
        FileSwitchCommand fileSwitchCommand = new FileSwitchCommand(testFolder, targetFolderName);
        createBothEmptyDataFile(targetFolderPath);

        assertCommandFailure(fileSwitchCommand, storage, model, expectedMessage);
    }

    @Test
    public void execute_targetFolderDoesNotExist_throwsCommandException() {
        String targetFolderName = "target";

        String expectedMessage = String.format(DataStorageManager.MESSAGE_FOLDER_DOES_NOT_EXIST, targetFolderName);

        FileSwitchCommand fileSwitchCommand = new FileSwitchCommand(testFolder, targetFolderName);

        assertCommandFailure(fileSwitchCommand, storage, model, expectedMessage);
    }

    @Test
    public void execute_invalidDataFolderFormat_throwsCommandException() throws Exception {
        String targetFolderName = "target";
        Path targetFolderPath = getTempFilePath(targetFolderName);

        String expectedMessage = String.format(FileSwitchCommand.MESSAGE_INVALID_FILE_DATA, targetFolderName);

        FileSwitchCommand fileSwitchCommand = new FileSwitchCommand(testFolder, targetFolderName);
        storage = new StorageStubThrowsDataConversionException();
        createBothEmptyDataFile(targetFolderPath);

        assertThrows(CommandException.class, expectedMessage, () -> fileSwitchCommand.execute(storage, model));
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() throws Exception {
        String targetFolderName = "target";
        Path targetFolderPath = getTempFilePath(targetFolderName);

        String expectedMessage = String.format(FileSwitchCommand.MESSAGE_FAILED, "switching");

        FileSwitchCommand fileSwitchCommand = new FileSwitchCommand(testFolder, targetFolderName);
        storage = new StorageStubThrowsIoException();
        createBothEmptyDataFile(targetFolderPath);

        assertThrows(CommandException.class, expectedMessage, () -> fileSwitchCommand.execute(storage, model));
    }

    /**
     * Creates a directory, an empty JSON resident data file and an empty JSON venue data file.
     */
    private void createBothEmptyDataFile(Path folderPath) throws IOException {
        createEmptyJsonVenueDataFile(folderPath);
        createEmptyJsonResidentDataFile(folderPath);
    }

    /**
     * Creates an empty JSON resident data file.
     */
    private void createEmptyJsonResidentDataFile(Path folderPath) throws IOException {
        Path filePath = folderPath.resolve(JsonResidentBookStorage.RESIDENT_DATA_PATH);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableResidentBook(new ResidentBook()), filePath);
    }

    /**
     * Creates an empty JSON venue data file.
     */
    private void createEmptyJsonVenueDataFile(Path folderPath) throws IOException {
        Path filePath = folderPath.resolve(JsonVenueBookStorage.VENUE_DATA_PATH);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableVenueBook(new VenueBook()), filePath);
    }

    /**
     * A storage stub designed specifically for testing {@code FileSwitchCommand}.
     */
    private static class StorageStubForFileSwitchTest extends StorageStub {
        @Override
        public Path getDataStorageFolderPath() {
            return null;
        }

        @Override
        public Optional<ReadOnlyResidentBook> readResidentBook(Path folderPath)
                throws DataConversionException, IOException {
            return Optional.of(new ResidentBook());
        }

        @Override
        public Optional<ReadOnlyVenueBook> readVenueBook(Path folderPath) {
            return Optional.of(new VenueBook());
        }
    }

    /**
     * A storage stub class that throws an {@code IOException} when the
     * @see #deleteResidentBookFile(Path) method is invoked.
     */
    private static class StorageStubThrowsDataConversionException extends StorageStubForFileSwitchTest {
        public static final DataConversionException DUMMY_IO_EXCEPTION = new DataConversionException(new IOException());

        @Override
        public Optional<ReadOnlyResidentBook> readResidentBook(Path filePath) throws DataConversionException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A storage stub class that throws an {@code IOException} when the
     * @see #deleteResidentBookFile(Path) method is invoked.
     */
    private static class StorageStubThrowsIoException extends StorageStubForFileSwitchTest {
        public static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

        @Override
        public Optional<ReadOnlyResidentBook> readResidentBook(Path folderPath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
