package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFilePaths.PATH_TO_JERRY_JPG;
import static seedu.address.testutil.TypicalFilePaths.PATH_TO_TOM_JPG;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.Storage;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ImportCommand.
 */
public class ImportCommandTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "ImportCommandTest");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path NON_EXISTENT_FILE = TEST_DATA_FOLDER.resolve("nonExistentFile.json");
    private static final Path NOT_JSON_FORMAT_FILE = TEST_DATA_FOLDER.resolve("notJsonFormatAddressBook.json");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");

    private static final Path VALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("validPersonsAddressBook.json");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCommand(null));
    }

    @Test
    public void execute_pathIsDirectory_throwsCommandException() {
        StorageStub storageStub = new StorageStub();
        assertThrows(CommandException.class,
                ImportCommand.MESSAGE_PATH_IS_DIRECTORY, () ->
                new ImportCommand(TEST_DATA_FOLDER).execute(model, storageStub));
    }

    @Test
    public void execute_fileDoesNotExist_throwsCommandException() {
        StorageStub storageStub = new StorageStub();
        assertThrows(CommandException.class,
                ImportCommand.MESSAGE_FILE_DOES_NOT_EXIST, () ->
                new ImportCommand(NON_EXISTENT_FILE).execute(model, storageStub));
    }

    @Test
    public void execute_notJsonFormatFile_throwsCommandException() {
        StorageStub storageStub = new StorageStub();
        assertThrows(CommandException.class,
                ImportCommand.MESSAGE_CONSTRAINTS_UNSATISFIED_TEMPLATE, () ->
                        new ImportCommand(NOT_JSON_FORMAT_FILE).execute(model, storageStub));
    }

    @Test
    public void execute_duplicatePersonsInFile_throwsCommandException() {
        StorageStub storageStub = new StorageStub();
        assertThrows(CommandException.class,
                ImportCommand.MESSAGE_CONSTRAINTS_UNSATISFIED_TEMPLATE, () ->
                        new ImportCommand(DUPLICATE_PERSON_FILE).execute(model, storageStub));
    }

    @Test
    public void execute_invalidPersonInFile_throwsCommandException() {
        StorageStub storageStub = new StorageStub();
        assertThrows(CommandException.class,
                ImportCommand.MESSAGE_CONSTRAINTS_UNSATISFIED_TEMPLATE, () ->
                        new ImportCommand(INVALID_PERSON_FILE).execute(model, storageStub));
    }

    @Test
    public void execute_personInAddressBookAlsoInFile_throwsCommandException() {
        StorageStub storageStub = new StorageStub();
        assertThrows(CommandException.class,
                ImportCommand.MESSAGE_DUPLICATE_PERSON, () ->
                new ImportCommand(TYPICAL_PERSONS_FILE).execute(model, storageStub));
    }

    @Test
    public void execute_fileWithNewPersons_importSuccessful() throws Exception {
        StorageStub storageStub = new StorageStub();
        CommandResult commandResult = new ImportCommand(VALID_PERSON_FILE).execute(model, storageStub);

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

    /**
     * A default storage stub that have all of the methods failing.
     */
    private class StorageStub implements Storage {

        @Override
        public Optional<ReadOnlyAddressBook> readAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getUserPrefsFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookStorage(AddressBookStorage addressBookStorage) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<UserPrefs> readUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void exportDisplayedListAddressBook(ReadOnlyAddressBook displayedListAddressBook, String filePathString)
                throws IOException {
            throw new AssertionError("This method should not be called.");
        }

    }

}


