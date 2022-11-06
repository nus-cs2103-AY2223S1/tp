package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of TaskList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private ArchivedTaskListStorage archivedTaskListStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, ArchivedTaskListStorage archivedTaskListStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.archivedTaskListStorage = archivedTaskListStorage;
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


    // ================ TaskList methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskList> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskList> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyTaskList addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyTaskList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ Archived Task methods ==============================

    @Override
    public Path getArchivedTaskListFilePath() {
        return archivedTaskListStorage.getArchivedTaskListFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskList> readArchivedTaskList() throws DataConversionException, IOException {
        return readArchivedTaskList(archivedTaskListStorage.getArchivedTaskListFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskList> readArchivedTaskList(Path filePath)
            throws DataConversionException, IOException {
        return archivedTaskListStorage.readArchivedTaskList(filePath);
    }

    @Override
    public void saveArchivedTaskList(ReadOnlyTaskList addressBook) throws IOException {
        saveArchivedTaskList(addressBook, archivedTaskListStorage.getArchivedTaskListFilePath());
    }

    @Override
    public void saveArchivedTaskList(ReadOnlyTaskList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write data file: " + filePath);
        archivedTaskListStorage.saveArchivedTaskList(addressBook, filePath);
    }

}
