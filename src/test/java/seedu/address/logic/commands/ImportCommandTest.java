package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_IMPORT_ERROR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_DUPLICATE_PERSONS;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_IMPORT_DATA_SUCCESS;
import static seedu.address.model.util.SampleDataUtil.getSamplePersons;
import static seedu.address.testutil.TestUtil.getFilePathInSandboxFolder;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ImportCommand}.
 */
public class ImportCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Path dummyPath = getFilePathInSandboxFolder("foobar.json");
    private Path dummyPath2 = Paths.get("foobar2.json");
    private File dummyFile = new File(dummyPath.toUri());

    private Path validCsv = Paths.get("src/test/data/ImportCsvTest/validCsv.csv");
    private Path invalidCsv = Paths.get("src/test/data/ImportCsvTest/invalidCsv.csv");

    @Test
    public void execute_validJson_success() {
        Model validModel = new ModelManager(new AddressBook(), new UserPrefs());
        Person validPerson = new PersonBuilder().build();
        validModel.addPerson(validPerson);
        JsonAddressBookStorage storage = new JsonAddressBookStorage(dummyPath);
        try {
            dummyFile.createNewFile();
            storage.saveAddressBook(validModel.getAddressBook());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImportCommand importCommand = new ImportCommand(dummyPath);

        String expectedMessage = String.format(MESSAGE_IMPORT_DATA_SUCCESS, dummyPath.getFileName());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(importCommand, model, expectedMessage, expectedModel);
        dummyFile.delete();
    }

    @Test
    public void execute_duplicateJson_success() {
        JsonAddressBookStorage storage = new JsonAddressBookStorage(dummyPath);
        try {
            dummyFile.createNewFile();
            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImportCommand importCommand = new ImportCommand(dummyPath);

        String expectedMessage = String.format(MESSAGE_DUPLICATE_PERSONS + MESSAGE_IMPORT_DATA_SUCCESS,
                dummyPath.getFileName());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(importCommand, model, expectedMessage, expectedModel);
        dummyFile.delete();
    }

    @Test
    public void execute_invalidJson_throwsCommandException() throws CommandException {
        JsonAddressBookStorage storage = new JsonAddressBookStorage(dummyPath);
        try {
            FileWriter writer = new FileWriter(dummyFile);
            writer.write("{}");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImportCommand importCommand = new ImportCommand(dummyPath);

        assertThrows(CommandException.class, () -> importCommand.execute(model));
        dummyFile.delete();
    }

    @Test
    public void execute_validCsv_success() {
        ImportCommand importCommand = new ImportCommand(validCsv);

        String expectedMessage = String.format(MESSAGE_IMPORT_DATA_SUCCESS, validCsv.getFileName());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        for (Person person : getSamplePersons()) {
            expectedModel.addPerson(person);
        }

        assertCommandSuccess(importCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateCsv_success() {
        ImportCommand importCommand = new ImportCommand(validCsv);

        String expectedMessage = String.format(MESSAGE_DUPLICATE_PERSONS + MESSAGE_IMPORT_DATA_SUCCESS,
                validCsv.getFileName());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        for (Person person : getSamplePersons()) {
            expectedModel.addPerson(person);
        }

        assertCommandSuccess(importCommand, expectedModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCsv_throwsCommandException() {
        ImportCommand importCommand = new ImportCommand(invalidCsv);

        assertCommandFailure(importCommand, model,
                String.format(MESSAGE_IMPORT_ERROR, "Error capturing CSV header!"));
    }

    @Test
    public void equals() {
        ImportCommand importFirstCommand = new ImportCommand(dummyPath);
        ImportCommand importSecondCommand = new ImportCommand(dummyPath2);

        // same object -> returns true
        assertTrue(importFirstCommand.equals(importFirstCommand));

        // same values -> returns true
        ImportCommand importFirstCommandCopy = new ImportCommand(dummyPath);
        assertTrue(importFirstCommand.equals(importFirstCommandCopy));

        // different types -> returns false
        assertFalse(importFirstCommand.equals(1));

        // null -> returns false
        assertFalse(importFirstCommand.equals(null));

        // different path -> returns false
        assertFalse(importFirstCommand.equals(importSecondCommand));
    }
}
