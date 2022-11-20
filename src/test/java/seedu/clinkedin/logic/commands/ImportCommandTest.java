package seedu.clinkedin.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinkedin.logic.commands.ImportCommand.MESSAGE_NO_CHANGE;
import static seedu.clinkedin.logic.commands.ImportCommand.MESSAGE_SOME_CHANGE;
import static seedu.clinkedin.logic.commands.ImportCommand.MESSAGE_SUCCESS;
import static seedu.clinkedin.testutil.Assert.assertThrows;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.exceptions.DataConversionException;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.logic.parser.ParserUtil.FileType;
import seedu.clinkedin.model.AddressBook;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.UserPrefs;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.util.SampleDataUtil;

class ImportCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        String filePath = "data.csv";
        FileType fileType = FileType.CSV;
        assertThrows(NullPointerException.class, () -> new ImportCommand(null, null));
        assertThrows(NullPointerException.class, () -> new ImportCommand(filePath, null));
        assertThrows(NullPointerException.class, () -> new ImportCommand(null, fileType));
    }

    @Test
    public void execute_nonExistentCsvFile_throwsCommandException() {
        String fileName = "non-existent.csv";
        ImportCommand command = new ImportCommand(fileName, FileType.CSV);
        String expectedMessage = "File couldn't be found!";
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_nonExistentJsonFile_throwsCommandException() {
        String fileName = "non-existent.json";
        ImportCommand command = new ImportCommand(fileName, FileType.JSON);
        String expectedMessage = "Couldn't read file properly. Check your file again!";
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    void execute_emptyCsvFile_throwsCommandException() throws IOException {
        String fileName = "empty.csv";
        createEmptyFile(fileName);
        ImportCommand command = new ImportCommand(fileName, FileType.CSV);
        String expectedMessage = "File is empty!";
        assertCommandFailure(command, model, expectedMessage);
        try {
            deleteFile(fileName);
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void execute_emptyJsonFile_throwsCommandException() throws IOException {
        String fileName = "empty.json";
        createEmptyFile(fileName);
        ImportCommand command = new ImportCommand(fileName, FileType.JSON);
        String expectedMessage = "Couldn't read file properly. Check your file again!";
        assertCommandFailure(command, model, expectedMessage);
        try {
            deleteFile(fileName);
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void execute_invalidCsvFile_throwsCommandException() {
        String fileName = "src/test/data/CsvExportedAddressBookTest/invalidPersonAdressBook.csv";
        ImportCommand command = new ImportCommand(fileName, FileType.CSV);
        String expectedMessage = "File format invalid or not compatible!";
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(model));
    }

    @Test
    void execute_invalidJsonFile_throwsCommandException() {
        String fileName = "src/test/data/JsonSerializableAddressBookTest/invalidPersonAddressBook.json";
        ImportCommand command = new ImportCommand(fileName, FileType.JSON);
        String expectedMessage = "Incompatible file format. Check your file again!";
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(model));
    }

    @Test
    void execute_samePersonsCsv_success() throws DataConversionException {
        String fileName = "src/test/data/CsvExportedAddressBookTest/typicalPersonsAddressBook.csv";
        ImportCommand command = new ImportCommand(fileName, FileType.CSV);
        String expectedMessage = String.format(MESSAGE_NO_CHANGE, fileName);
        Model sampleModel = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs());
        assertCommandSuccess(command, sampleModel, expectedMessage, sampleModel);
    }

    @Test
    void execute_samePersonsJson_success() throws DataConversionException {
        String fileName = "src/test/data/JsonSerializableAddressBookTest/sampleDataPersonsAddressBook.json";
        ImportCommand command = new ImportCommand(fileName, FileType.JSON);
        String expectedMessage = String.format(MESSAGE_NO_CHANGE, fileName);
        Model sampleModel = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs());
        assertCommandSuccess(command, sampleModel, expectedMessage, sampleModel);
    }

    @Test
    void execute_somePersonsExistCsv_success() throws DataConversionException, CommandException {
        String fileName = "src/test/data/CsvExportedAddressBookTest/typicalPersonsAddressBook.csv";
        ImportCommand command = new ImportCommand(fileName, FileType.CSV);
        String expectedMessage = MESSAGE_SUCCESS + " " + MESSAGE_SOME_CHANGE;
        Model expectedModel = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs());
        Model sampleModel = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs());
        Person p = sampleModel.getFilteredPersonList().get(1);
        sampleModel.deletePerson(p);
        new SortCommand().execute(sampleModel);
        new SortCommand().execute(expectedModel);
        assertCommandSuccess(command, sampleModel, expectedMessage, expectedModel);
    }

    @Test
    void execute_somePersonsExistJson_success() throws DataConversionException, CommandException {
        String fileName = "src/test/data/JsonSerializableAddressBookTest/sampleDataPersonsAddressBook.json";
        ImportCommand command = new ImportCommand(fileName, FileType.JSON);
        String expectedMessage = MESSAGE_SUCCESS + " " + MESSAGE_SOME_CHANGE;
        Model expectedModel = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs());
        Model sampleModel = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs());
        Person p = sampleModel.getFilteredPersonList().get(1);
        sampleModel.deletePerson(p);
        new SortCommand().execute(sampleModel);
        new SortCommand().execute(expectedModel);
        assertCommandSuccess(command, sampleModel, expectedMessage, expectedModel);
    }

    @Test
    void execute_emptyAddressBookCsv_success() throws DataConversionException {
        String fileName = "src/test/data/CsvExportedAddressBookTest/typicalPersonsAddressBook.csv";
        ImportCommand command = new ImportCommand(fileName, FileType.CSV);
        String expectedMessage = MESSAGE_SUCCESS;
        Model expectedModel = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs());
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs());
        assertCommandSuccess(command, testModel, expectedMessage, expectedModel);
    }

    @Test
    void execute_emptyAddressBookJson_success() throws DataConversionException {
        String fileName = "src/test/data/JsonSerializableAddressBookTest/sampleDataPersonsAddressBook.json";
        ImportCommand command = new ImportCommand(fileName, FileType.JSON);
        String expectedMessage = MESSAGE_SUCCESS;
        Model expectedModel = new ModelManager(SampleDataUtil.getSampleAddressBook(), new UserPrefs());
        Model testModel = new ModelManager(new AddressBook(), new UserPrefs());
        assertCommandSuccess(command, testModel, expectedMessage, expectedModel);
    }
    @Test
    public void equals() {

        ImportCommand firstImportCommand = new ImportCommand("same.csv", FileType.CSV);
        ImportCommand secondImportCommand = new ImportCommand("same.csv", FileType.CSV);
        ImportCommand thirdImportCommand = new ImportCommand("same.json", FileType.JSON);
        ImportCommand fourthImportCommand = new ImportCommand("different.csv", FileType.CSV);
        ImportCommand fifthImportCommand = new ImportCommand("different.json", FileType.JSON);
        // same object -> returns true
        assertTrue(firstImportCommand.equals(firstImportCommand));

        // same command -> returns true
        assertTrue(firstImportCommand.equals(secondImportCommand));

        // different file name extension -> return false
        assertFalse(firstImportCommand.equals(thirdImportCommand));

        // different file name -> return false
        assertFalse(firstImportCommand.equals(fourthImportCommand));

        // different file name and extension -> return false
        assertFalse(firstImportCommand.equals(fifthImportCommand));
        // null -> returns false
        assertFalse(firstImportCommand.equals(null));
    }
    public void createEmptyFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
    }

    public void deleteFile(String fileName) throws IOException {
        File file = new File(fileName);
        file.delete();
    }
}
