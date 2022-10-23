package seedu.rc4hdb.logic.commands.storagecommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import java.nio.file.Path;

import seedu.rc4hdb.commons.util.FileUtil;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.storage.JsonResidentBookStorage;
import seedu.rc4hdb.storage.JsonUserPrefsStorage;
import seedu.rc4hdb.storage.ResidentBookStorage;
import seedu.rc4hdb.storage.Storage;
import seedu.rc4hdb.storage.StorageManager;
import seedu.rc4hdb.storage.UserPrefsStorage;

/**
 * Contains helper methods for testing storage commands.
 */
public class StorageCommandTestUtil {

    public static final String VALID_FILE_NAME_STRING = "test";
    public static final String VALID_FILE_NAME_STRING_JSON = "data/" + VALID_FILE_NAME_STRING + ".json";
    public static final Path VALID_FILE_NAME_PATH = Path.of(VALID_FILE_NAME_STRING_JSON);

    public static final String INVALID_FILE_NAME_FULL_STOP = "test.";
    public static final String INVALID_FILE_NAME_WHITESPACE = "te st";
    public static final String INVALID_FILE_NAME_FORWARD_SLASH = "/test";
    public static final String INVALID_FILE_NAME_BACKSLASH = "\\test";

    //=================== Start of helper methods ==============================

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualStorage} matches {@code expectedStorage}
     */
    public static void assertCommandSuccess(StorageCommand command, Storage actualStorage,
                                            CommandResult expectedCommandResult, Storage expectedStorage) {
        try {
            CommandResult result = command.execute(actualStorage);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedStorage, actualStorage);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(StorageCommand, Storage, CommandResult, Storage)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(StorageCommand command, Storage actualStorage, String expectedMessage,
                                            Storage expectedStorage) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualStorage, expectedCommandResult, expectedStorage);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the resident book storage and user prefs storage in {@code actualStorage} remain unchanged
     */
    public static void assertCommandFailure(StorageCommand command, Storage actualStorage, String expectedMessage) {
        // we are unable to defensively copy the storage for comparison later, so we can
        // only do so by copying its components.
        ResidentBookStorage expectedResidentBookStorage =
                new JsonResidentBookStorage(actualStorage.getResidentBookFilePath());
        UserPrefsStorage expectedUserPrefsStorage = new JsonUserPrefsStorage(actualStorage.getUserPrefsFilePath());
        Storage expectedStorage = new StorageManager(expectedResidentBookStorage, expectedUserPrefsStorage);

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualStorage));
        assertEquals(expectedStorage, actualStorage);
    }

    /**
     * Confirms that the file exists.
     */
    public static void assertFileExists(Path filePath) {
        assertTrue(FileUtil.isFileExists(filePath));
    }

    /**
     * Confirms that the file does not exist.
     */
    public static void assertFileDoesNotExist(Path filePath) {
        assertFalse(FileUtil.isFileExists(filePath));
    }

}
