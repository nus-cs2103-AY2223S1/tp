package hobbylist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import hobbylist.commons.core.LogsCenter;
import hobbylist.commons.exceptions.DataConversionException;
import hobbylist.model.ReadOnlyHobbyList;
import hobbylist.model.ReadOnlyUserPrefs;
import hobbylist.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private HobbyListStorage hobbyListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(HobbyListStorage hobbyListStorage, UserPrefsStorage userPrefsStorage) {
        this.hobbyListStorage = hobbyListStorage;
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
    public Path getAddressBookFilePath() {
        return hobbyListStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyHobbyList> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(hobbyListStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyHobbyList> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return hobbyListStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyHobbyList addressBook) throws IOException {
        saveAddressBook(addressBook, hobbyListStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyHobbyList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        hobbyListStorage.saveAddressBook(addressBook, filePath);
    }

}
