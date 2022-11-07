package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinkedin.testutil.Assert.assertThrows;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.logic.parser.ParserUtil.FileType;
import seedu.clinkedin.model.AddressBook;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.UserPrefs;


class ExportCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        String filePath = "data.csv";
        FileType fileType = FileType.CSV;
        assertThrows(NullPointerException.class, () -> new ExportCommand(null, null));
        assertThrows(NullPointerException.class, () -> new ExportCommand(filePath, null));
        assertThrows(NullPointerException.class, () -> new ExportCommand(null, fileType));
    }

    @Test
    public void execute_exportInCsvFormat_success() {
        String fileName = "data.csv";
        ExportCommand command = new ExportCommand(fileName, FileType.CSV);
        String expectedMessage = String.format(ExportCommand.MESSAGE_SUCCESS, fileName);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        try {
            new ImportCommand(fileName, FileType.CSV).execute(emptyModel);
            assertEquals(model.getFilteredPersonList(), emptyModel.getFilteredPersonList());
        } catch (CommandException e) {
            fail("Exception not expected");
        }
        try {
            deleteFile(fileName);
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }

    @Test
    public void execute_exportInJsonFormat_success() {
        String fileName = "data.json";
        ExportCommand command = new ExportCommand(fileName, FileType.JSON);
        String expectedMessage = String.format(ExportCommand.MESSAGE_SUCCESS, fileName);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        try {
            new ImportCommand(fileName, FileType.JSON).execute(emptyModel);
            assertEquals(model.getFilteredPersonList(), emptyModel.getFilteredPersonList());
        } catch (CommandException e) {
            fail("Exception not expected");
        }
        try {
            deleteFile(fileName);
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }
    @Test
    public void equals_sameCommand_returnsTrue() {

        ExportCommand firstExportCommand = new ExportCommand("same.csv", FileType.CSV);
        ExportCommand secondExportCommand = new ExportCommand("same.csv", FileType.CSV);

        // same object -> returns true
        assertTrue(firstExportCommand.equals(firstExportCommand));

        // same path and fileType -> returns true
        assertTrue(firstExportCommand.equals(secondExportCommand));
    }

    @Test
    public void equals_samePathDifferentType_returnsFalse() {

        ExportCommand firstExportCommand = new ExportCommand("same.csv", FileType.CSV);
        ExportCommand secondExportCommand = new ExportCommand("same.csv", FileType.JSON);

        assertFalse(firstExportCommand.equals(secondExportCommand));
    }

    @Test
    public void equals_sameTypeDifferentPath_returnsFalse() {

        ExportCommand firstExportCommand = new ExportCommand("same.csv", FileType.CSV);
        ExportCommand secondExportCommand = new ExportCommand("different.csv", FileType.CSV);

        assertFalse(firstExportCommand.equals(secondExportCommand));
    }

    @Test
    public void equals_differentCommand_returnsFalse() {

        ExportCommand firstExportCommand = new ExportCommand("same.csv", FileType.CSV);
        ExportCommand secondExportCommand = new ExportCommand("different.json", FileType.JSON);

        assertFalse(firstExportCommand.equals(secondExportCommand));
    }

    public void deleteFile(String fileName) throws IOException {
        File file = new File(fileName);
        file.delete();
    }
}
