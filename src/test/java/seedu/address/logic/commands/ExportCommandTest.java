package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getFilePathInSandboxFolder;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ImportCommand}.
 */
public class ExportCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Path dummyPath = Paths.get("foobar.csv");
    private Path validCsvPath = getFilePathInSandboxFolder("foobar.csv");
    private Path invalidCsvPath = getFilePathInSandboxFolder("nonexistent/foobar.csv");

    @Test
    public void execute_validCsvPath_success() {
        ExportCommand exportCommand = new ExportCommand(validCsvPath);

        String expectedMessage = String.format(ExportCommand.MESSAGE_EXPORT_DATA_SUCCESS, validCsvPath.getFileName());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(exportCommand, model, expectedMessage, expectedModel);

        //Reimport exported CSV file and checking if model remains the same
        Model importedModel = new ModelManager(new AddressBook(), new UserPrefs());
        ImportCommand importCommand = new ImportCommand(validCsvPath);
        try {
            importCommand.execute(importedModel);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
        assertEquals(model, importedModel);
        new File(validCsvPath.toUri()).delete();
    }

    @Test
    public void execute_invalidCsvPath_throwsCommandException() {
        ExportCommand exportCommand = new ExportCommand(invalidCsvPath);

        assertThrows(CommandException.class, () -> exportCommand.execute(model));
    }

    @Test
    public void equals() {
        ExportCommand exportFirstCommand = new ExportCommand(validCsvPath);
        ExportCommand exportSecondCommand = new ExportCommand(dummyPath);

        // same object -> returns true
        assertTrue(exportFirstCommand.equals(exportFirstCommand));

        // same values -> returns true
        ExportCommand exportFirstCommandCopy = new ExportCommand(validCsvPath);
        assertTrue(exportFirstCommand.equals(exportFirstCommandCopy));

        // different types -> returns false
        assertFalse(exportFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exportFirstCommand.equals(null));

        // different path -> returns false
        assertFalse(exportFirstCommand.equals(exportSecondCommand));
    }
}
