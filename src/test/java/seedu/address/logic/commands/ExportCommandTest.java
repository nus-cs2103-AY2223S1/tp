package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.Storage;

public class ExportCommandTest {

    @Test
    public void execute_emptyDisplayedList_success() {
        Model model = new ModelManager();
        StorageStub storageStub = new StorageStub();
        assertDoesNotThrow(() -> new ExportCommand().execute(model, storageStub));
    }

    @Test
    public void execute_nonEmptyDisplayedList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        StorageStub storageStub = new StorageStub();
        assertDoesNotThrow(() -> new ExportCommand().execute(model, storageStub));
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

    }
}
