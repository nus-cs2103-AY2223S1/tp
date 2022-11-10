package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;

import seedu.address.storage.AddressBookStorage;

/**
 * A default storage stub that have all of its methods failing.
 */
public class AddressBookStorageStub implements AddressBookStorage {
    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

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

}
