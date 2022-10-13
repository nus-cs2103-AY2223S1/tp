package seedu.rc4hdb.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.rc4hdb.commons.core.LogsCenter;
import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ReadOnlyUserPrefs;
import seedu.rc4hdb.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ResidentBookStorage residentBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ResidentBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ResidentBookStorage residentBookStorage, UserPrefsStorage userPrefsStorage) {
        this.residentBookStorage = residentBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    /**
     * Creates a defensive copy of the {@code StorageManager}.
     * @return the defensive copy.
     */
    public StorageManager getCopy() {
        return new StorageManager(residentBookStorage, userPrefsStorage);
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

    // ================ ResidentBook methods =============================

    @Override
    public Path getResidentBookFilePath() {
        return residentBookStorage.getResidentBookFilePath();
    }

    @Override
    public void setResidentBookFilePath(Path filePath) {
        residentBookStorage.setResidentBookFilePath(filePath);
    }

    @Override
    public Optional<ReadOnlyResidentBook> readResidentBook() throws DataConversionException, IOException {
        return readResidentBook(residentBookStorage.getResidentBookFilePath());
    }

    @Override
    public Optional<ReadOnlyResidentBook> readResidentBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return residentBookStorage.readResidentBook(filePath);
    }

    @Override
    public void saveResidentBook(ReadOnlyResidentBook residentBook) throws IOException {
        saveResidentBook(residentBook, residentBookStorage.getResidentBookFilePath());
    }

    @Override
    public void saveResidentBook(ReadOnlyResidentBook residentBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        residentBookStorage.saveResidentBook(residentBook, filePath);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof StorageManager)) {
            return false;
        }

        // state check
        StorageManager other = (StorageManager) obj;
        return residentBookStorage.equals(other.residentBookStorage)
                && userPrefsStorage.equals(other.userPrefsStorage);
    }

}
