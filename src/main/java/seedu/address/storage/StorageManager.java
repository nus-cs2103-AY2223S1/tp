package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyApplicationBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ApplicationBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ApplicationBookStorage applicationBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ApplicationBookStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(ApplicationBookStorage applicationBookStorage, UserPrefsStorage userPrefsStorage) {
        this.applicationBookStorage = applicationBookStorage;
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


    // ================ ApplicationBook methods ==============================

    @Override
    public Path getApplicationBookFilePath() {
        return applicationBookStorage.getApplicationBookFilePath();
    }

    @Override
    public Optional<ReadOnlyApplicationBook> readApplicationBook() throws DataConversionException, IOException {
        return readApplicationBook(applicationBookStorage.getApplicationBookFilePath());
    }

    @Override
    public Optional<ReadOnlyApplicationBook> readApplicationBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return applicationBookStorage.readApplicationBook(filePath);
    }

    @Override
    public void saveApplicationBook(ReadOnlyApplicationBook applicationBook) throws IOException {
        saveApplicationBook(applicationBook, applicationBookStorage.getApplicationBookFilePath());
    }

    @Override
    public void saveApplicationBook(ReadOnlyApplicationBook applicationBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        applicationBookStorage.saveApplicationBook(applicationBook, filePath);
    }

}
