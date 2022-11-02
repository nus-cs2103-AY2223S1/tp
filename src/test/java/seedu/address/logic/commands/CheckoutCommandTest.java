package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
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

class CheckoutCommandTest {

    private final Path validPath = Paths.get("data", "test.json");
    private final UserPrefsStorage validUserPrefsStorage = new JsonUserPrefsStorage(Paths.get("preferences.json"));
    private final ModelStub modelStub = new ModelStub();
    private final StorageStub storageStub = new StorageStub();

    @Test
    public void constructor_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CheckoutCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new CheckoutCommand(validPath).execute(null, storageStub));
    }

    @Test
    public void execute_nullStorage_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            new CheckoutCommand(validPath).execute(modelStub, null));
    }

    @Test
    public void execute_sameBook_throwsCommandException() {
        Model validModel = new ModelManager();
        validModel.setAddressBookFilePath(validPath);
        assertThrows(CommandException.class, () ->
            new CheckoutCommand(validPath).execute(validModel, storageStub));
    }

    @Test
    public void execute_allValidParameters_checkoutSuccessful() throws CommandException {
        Storage validStorage = new StorageManager(new JsonAddressBookStorage(validPath), validUserPrefsStorage);
        Model validModel = new ModelManager();

        CommandResult commandResult = new CheckoutCommand(validPath).execute(validModel, validStorage);

        assertEquals(CheckoutCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(validPath, validModel.getAddressBookFilePath());
    }

    @Test
    public void equals() {
        final CheckoutCommand checkoutJuneCommand = new CheckoutCommand(validPath);
        final CheckoutCommand checkoutJulyCommand = new CheckoutCommand(Paths.get("data", "june-2022.json"));

        // same object -> returns true
        assertTrue(checkoutJuneCommand.equals(checkoutJuneCommand));

        // same values -> returns true
        final CheckoutCommand checkoutJuneCommandCopy = new CheckoutCommand(validPath);
        assertTrue(checkoutJuneCommand.equals(checkoutJuneCommandCopy));

        // different types -> returns false
        assertFalse(checkoutJuneCommand.equals(1));

        // null -> returns false
        assertFalse(checkoutJuneCommand.equals(null));

        // different person -> returns false
        assertFalse(checkoutJuneCommand.equals(checkoutJulyCommand));
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
