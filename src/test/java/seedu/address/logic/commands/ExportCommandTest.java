package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;

public class ExportCommandTest {

    private final Path validPath = Paths.get("data", "test.json");
    private final UserPrefsStorage validUserPrefsStorage = new JsonUserPrefsStorage(Paths.get("preferences.json"));
    private final ModelStub modelStub = new ModelStub();
    private final StorageStub storageStub = new StorageStub();
    private final Storage validStorage = new StorageManager(
            new JsonAddressBookStorage(validPath), validUserPrefsStorage);
    private final Model validEmptyModel = new ModelManager();
    private final Model validNonEmptyModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new ExportCommand().execute(null, storageStub));
    }

    @Test
    public void execute_nullStorage_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new ExportCommand().execute(modelStub, null));
    }

    @Test
    public void execute_emptyDisplayedList_success() {
        assertDoesNotThrow(() -> new ExportCommand().execute(validEmptyModel, validStorage));
    }

    @Test
    public void execute_nonEmptyDisplayedList_success() {
        assertDoesNotThrow(() -> new ExportCommand().execute(validNonEmptyModel, validStorage));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersons(AddressBook addressBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void appendAddressBook(AddressBook addressBook) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getViewedPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateViewedPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
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
