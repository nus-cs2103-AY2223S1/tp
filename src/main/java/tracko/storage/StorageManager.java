package tracko.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import tracko.commons.core.LogsCenter;
import tracko.commons.exceptions.DataConversionException;
import tracko.model.ReadOnlyAddressBook;
import tracko.model.ReadOnlyTrackO;
import tracko.model.ReadOnlyUserPrefs;
import tracko.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private TrackOStorage trackOStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, TrackOStorage trackOStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.trackOStorage = trackOStorage;
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

    // ============== TrackO methods =========================================

    @Override
    public Path getOrdersFilePath() {
        return trackOStorage.getOrdersFilePath();
    }

    @Override
    public Optional<ReadOnlyTrackO> readTrackO() throws DataConversionException, IOException {
        return readTrackO(trackOStorage.getOrdersFilePath());
    }

    @Override
    public Optional<ReadOnlyTrackO> readTrackO(Path ordersFilePath) throws DataConversionException, IOException {
        logger.fine("[TrackO] Attempting to read data from file: " + ordersFilePath);
        return trackOStorage.readTrackO(ordersFilePath);
    }

    @Override
    public void saveTrackO(ReadOnlyTrackO trackO) throws IOException {
        saveTrackO(trackO, trackOStorage.getOrdersFilePath());
    }

    @Override
    public void saveTrackO(ReadOnlyTrackO trackO, Path ordersFilePath) throws IOException {
        logger.fine("[TrackO] Attempting to write to data file: " + ordersFilePath);
        trackOStorage.saveTrackO(trackO, ordersFilePath);
    }

}
