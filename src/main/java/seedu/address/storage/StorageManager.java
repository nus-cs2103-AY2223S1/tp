package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyDatabase;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Database data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private DatabaseStorage databaseStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code DatabaseStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(DatabaseStorage databaseStorage, UserPrefsStorage userPrefsStorage) {
        this.databaseStorage = databaseStorage;
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

    // ================ Database methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return databaseStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyDatabase> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(databaseStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyDatabase> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return databaseStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyDatabase addressBook) throws IOException {
        saveAddressBook(addressBook, databaseStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyDatabase addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        databaseStorage.saveAddressBook(addressBook, filePath);
    }

}
