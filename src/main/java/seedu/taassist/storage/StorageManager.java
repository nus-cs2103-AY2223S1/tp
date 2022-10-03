package seedu.taassist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.taassist.commons.core.LogsCenter;
import seedu.taassist.commons.exceptions.DataConversionException;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.ReadOnlyUserPrefs;
import seedu.taassist.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TaAssistStorage taAssistStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TaAssistStorage taAssistStorage, UserPrefsStorage userPrefsStorage) {
        this.taAssistStorage = taAssistStorage;
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
        return taAssistStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyTaAssist> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(taAssistStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyTaAssist> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taAssistStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyTaAssist addressBook) throws IOException {
        saveAddressBook(addressBook, taAssistStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyTaAssist addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taAssistStorage.saveAddressBook(addressBook, filePath);
    }

}
