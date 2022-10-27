package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private ArchivedTaskBookStorage archivedTaskBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, ArchivedTaskBookStorage archivedTaskBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.archivedTaskBookStorage = archivedTaskBookStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ Archived Task methods ==============================

    @Override
    public Path getArchivedTaskBookFilePath() {
        return archivedTaskBookStorage.getArchivedTaskBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readArchivedTaskBook() throws DataConversionException, IOException {
        return readArchivedTaskBook(archivedTaskBookStorage.getArchivedTaskBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readArchivedTaskBook(Path filePath)
            throws DataConversionException, IOException {
        return archivedTaskBookStorage.readArchivedTaskBook(filePath);
    }

    @Override
    public void saveArchivedTaskBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveArchivedTaskBook(addressBook, archivedTaskBookStorage.getArchivedTaskBookFilePath());
    }

    @Override
    public void saveArchivedTaskBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write data file: " + filePath);
        archivedTaskBookStorage.saveArchivedTaskBook(addressBook, filePath);
    }

}
