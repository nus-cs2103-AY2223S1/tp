package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyClientBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ClientBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ClientBookStorage clientBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ClientBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ClientBookStorage clientBookStorage, UserPrefsStorage userPrefsStorage) {
        this.clientBookStorage = clientBookStorage;
        this.userPrefsStorage = userPrefsStorage;
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
    public Path getClientBookFilePath() {
        return addressBookStorage.getClientBookFilePath();
    }

    @Override
    public Optional<ReadOnlyClientBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getClientBookFilePath());
    }

    @Override
    public Optional<ReadOnlyClientBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveClientBook(ReadOnlyClientBook addressBook) throws IOException {
        saveClientBook(addressBook, addressBookStorage.getClientBookFilePath());
    }

    @Override
    public void saveClientBook(ReadOnlyClientBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveClientBook(addressBook, filePath);
    }

}
