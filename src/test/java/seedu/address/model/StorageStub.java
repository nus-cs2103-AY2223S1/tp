package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;

import seedu.address.storage.Storage;

public class StorageStub implements Storage {

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getUserPrefsFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public String saveIterationImage(String src) {
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
