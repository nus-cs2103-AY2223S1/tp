package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_KIV;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_CONSTRAINTS_UNSATISFIED;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_FILE_DOES_NOT_EXIST;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_PATH_IS_DIRECTORY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFilePaths.PATH_TO_JERRY_JPG;
import static seedu.address.testutil.TypicalFilePaths.PATH_TO_TOM_JPG;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ImportCommand.
 */
public class ImportCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    Path baseDir = Paths.get("src", "test", "data", "ImportCommandTest");

    @Test
    public void constructor_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCommand(null));
    }

    @Test
    public void execute_pathIsDirectory_throwsCommandException() {
        assertThrows(CommandException.class,
                ImportCommand.MESSAGE_PATH_IS_DIRECTORY,
                () -> new ImportCommand(baseDir).execute(model));
    }

    @Test
    public void execute_fileDoesNotExist_throwsCommandException() {
        Path path = baseDir.resolve("nonExistentFile");
        assertThrows(CommandException.class,
                ImportCommand.MESSAGE_FILE_DOES_NOT_EXIST,
                () -> new ImportCommand(path).execute(model));
    }

    @Test
    public void execute_duplicatePersonsInFile_throwsCommandException() {
        Path path = baseDir.resolve("duplicatePersonAddressBook.json");
        assertThrows(CommandException.class,
                ImportCommand.MESSAGE_CONSTRAINTS_UNSATISFIED,
                () -> new ImportCommand(path).execute(model));
    }

    @Test
    public void execute_invalidPersonInFile_throwsCommandException() {
        Path path = baseDir.resolve("duplicatePersonAddressBook.json");
        assertThrows(CommandException.class,
                ImportCommand.MESSAGE_CONSTRAINTS_UNSATISFIED,
                () -> new ImportCommand(path).execute(model));
    }

    @Test
    public void execute_personInAddressBookAlsoInFile_throwsCommandException() {
        Path path = baseDir.resolve("typicalPersonsAddressBook.json");
        assertThrows(CommandException.class,
                ImportCommand.MESSAGE_DUPLICATE_PERSON,
                () -> new ImportCommand(path).execute(model));
    }

    @Test
    public void execute_fileWithNewPersons_importSuccessful() throws Exception {
        Path path = baseDir.resolve("validPersonsAddressBook.json");

        CommandResult commandResult = new ImportCommand(path).execute(model);

        assertEquals(String.format(ImportCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
        assertTrue(model.hasPerson(HOON));
        assertTrue(model.hasPerson(IDA));
    }

    @Test
    public void equals() {
        final ImportCommand importJerryCommand = new ImportCommand(PATH_TO_JERRY_JPG);
        final ImportCommand importTomCommand = new ImportCommand(PATH_TO_TOM_JPG);

        // same object -> returns true
        assertTrue(importJerryCommand.equals(importJerryCommand));

        // same values -> returns true
        ImportCommand importJerryCommandCopy = new ImportCommand(PATH_TO_JERRY_JPG);
        assertTrue(importJerryCommand.equals(importJerryCommandCopy));

        // different types -> returns false
        assertFalse(importJerryCommand.equals(1));

        // null -> returns false
        assertFalse(importJerryCommand.equals(null));

        // different person -> returns false
        assertFalse(importJerryCommand.equals(importTomCommand));
    }


}


