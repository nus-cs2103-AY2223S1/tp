package seedu.rc4hdb.logic.commands.filecommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.rc4hdb.commons.core.FilePostfixes.CSV_POSTFIX;
import static seedu.rc4hdb.logic.commands.filecommands.FileCommand.MESSAGE_FAILED;
import static seedu.rc4hdb.logic.commands.filecommands.FileCommand.MESSAGE_FILE_ALREADY_EXISTS;
import static seedu.rc4hdb.logic.commands.filecommands.ImportCommand.MESSAGE_FILE_DOES_NOT_EXIST;
import static seedu.rc4hdb.logic.commands.filecommands.ImportCommand.MESSAGE_INVALID_FILE_DATA;
import static seedu.rc4hdb.logic.commands.filecommands.ImportCommand.MESSAGE_SUCCESS;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.StorageCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.storage.Storage;
import seedu.rc4hdb.storage.StorageStub;

/**
 * Unit tests for {@link ImportCommand}.
 */
public class ImportCommandTest {

    public static final String DUMMY_PATH_STRING = "dummy";
    public static final Path DUMMY_PATH = Path.of(DUMMY_PATH_STRING);

    private ImportCommand importCommand = new ImportCommand(DUMMY_PATH, DUMMY_PATH_STRING);

    @Test
    public void execute_validImportCommand_commandSuccess() throws CommandException {
        CommandResult result = importCommand.execute(new StorageStubForImportCommand());
        assertEquals(result.getFeedbackToUser(), String.format(MESSAGE_SUCCESS, DUMMY_PATH_STRING + CSV_POSTFIX));
    }

    @Test
    public void execute_storageThrowIoException_throwCommandException() {
        assertCommandFailure(importCommand, new StorageStubReadCsvThrowIoException(),
                String.format(MESSAGE_FAILED, "importing"));
    }

    @Test
    public void execute_invalidCsvFile_throwCommandException() {
        assertCommandFailure(importCommand, new StorageStubReadCsvThrowDataConversionException(),
                String.format(MESSAGE_INVALID_FILE_DATA, DUMMY_PATH + CSV_POSTFIX, ""));
    }

    @Test
    public void execute_csvFileDoesNotExist_throwsCommandException() {
        assertCommandFailure(importCommand, new StorageStubCsvFileDoesNotExist(),
                String.format(MESSAGE_FILE_DOES_NOT_EXIST, DUMMY_PATH_STRING + CSV_POSTFIX));
    }

    @Test
    public void execute_folderAlreadyExists_throwCommandException() {
        assertCommandFailure(importCommand, new StorageStubCreateFileThrowFileAlreadyExistsException(),
                String.format(MESSAGE_FILE_ALREADY_EXISTS, DUMMY_PATH_STRING));
    }

    //===================== Start of helper functions =============================

    /**
     * Checks if the storage command fails by checking the following
     * - execution of the command throws {@code CommandException}
     * - returns the same {@code} CommandResult
     */
    public void assertCommandFailure(StorageCommand storageCommand, Storage storage, CommandResult expectedResult) {
        assertThrows(CommandException.class, expectedResult.getFeedbackToUser(), () -> storageCommand.execute(storage));
    }

    /**
     * Convenience method for
     * @see #assertCommandFailure(StorageCommand, Storage, CommandResult)
     */
    public void assertCommandFailure(StorageCommand storageCommand, Storage storage, String expectedMessage) {
        assertCommandFailure(storageCommand, storage, new CommandResult(expectedMessage));
    }

    //==================== Start of storage stubs ==================================

    /**
     * A storage stub designed specifically for testing {@code ImportCommand}.
     */
    private static class StorageStubForImportCommand extends StorageStub {
        @Override
        public Optional<ReadOnlyResidentBook> readCsvFile(Path filePath) throws IOException, DataConversionException {
            return Optional.of(new ResidentBook());
        }

        @Override
        public void createDataFolder(Path folderPath) throws IOException {
            // does nothing
        }

        @Override
        public void saveResidentBook(ReadOnlyResidentBook residentBook, Path folderPath) {
            // does nothing
        }
    }

    private static class StorageStubReadCsvThrowIoException extends StorageStubForImportCommand {
        @Override
        public Optional<ReadOnlyResidentBook> readCsvFile(Path filePath) throws IOException {
            throw new IOException();
        }
    }

    private static class StorageStubReadCsvThrowDataConversionException extends StorageStubForImportCommand {
        @Override
        public Optional<ReadOnlyResidentBook> readCsvFile(Path filePath) throws DataConversionException {
            throw new DataConversionException(new Exception());
        }
    }

    private static class StorageStubCsvFileDoesNotExist extends StorageStubForImportCommand {
        @Override
        public Optional<ReadOnlyResidentBook> readCsvFile(Path filePath) {
            return Optional.empty();
        }
    }

    private static class StorageStubCreateFileThrowFileAlreadyExistsException extends StorageStubForImportCommand {
        @Override
        public void createDataFolder(Path filePath) throws FileAlreadyExistsException {
            throw new FileAlreadyExistsException(filePath.toString());
        }
    }

}
