package seedu.rc4hdb.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.rc4hdb.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ResidentBook;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.storage.JsonResidentBookStorage;
import seedu.rc4hdb.storage.JsonUserPrefsStorage;
import seedu.rc4hdb.storage.ResidentBookStorage;
import seedu.rc4hdb.storage.Storage;
import seedu.rc4hdb.storage.StorageManager;
import seedu.rc4hdb.storage.UserPrefsStorage;

/**
 * Contains helper methods for testing storage model commands.
 */
public class StorageModelCommandTestUtil {

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualStorage} matches {@code expectedStorage}
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(StorageModelCommand command, Storage actualStorage, Model actualModel,
            CommandResult expectedCommandResult, Storage expectedStorage, Model expectedModel) {
        try {
            CommandResult result = command.execute(actualStorage, actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedStorage, actualStorage);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to
     * {@link #assertCommandSuccess(StorageModelCommand, Storage, Model, CommandResult, Storage, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(StorageModelCommand command, Storage actualStorage, Model actualModel,
            String expectedMessage, Storage expectedStorage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualStorage, actualModel, expectedCommandResult, expectedStorage,
                expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the resident book storage and user prefs storage in {@code actualStorage} remain unchanged
     * - the resident book, filtered resident list and selected resident in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(StorageModelCommand command, Storage actualStorage, Model actualModel,
            String expectedMessage) {
        // we are unable to defensively copy the storage and model for comparison later, so we can
        // only do so by copying its components.
        ResidentBookStorage expectedResidentBookStorage =
                new JsonResidentBookStorage(actualStorage.getResidentBookFilePath());
        UserPrefsStorage expectedUserPrefsStorage = new JsonUserPrefsStorage(actualStorage.getUserPrefsFilePath());
        Storage expectedStorage = new StorageManager(expectedResidentBookStorage, expectedUserPrefsStorage);

        ResidentBook expectedResidentBook = new ResidentBook(actualModel.getResidentBook());
        List<Resident> expectedFilteredList = new ArrayList<>(actualModel.getFilteredResidentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualStorage, actualModel));
        assertEquals(expectedStorage, actualStorage);
        assertEquals(expectedResidentBook, actualModel.getResidentBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredResidentList());
    }

}
