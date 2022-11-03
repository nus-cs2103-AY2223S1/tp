package seedu.watson.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.watson.commons.core.LogsCenter;
import seedu.watson.commons.exceptions.DataConversionException;
import seedu.watson.model.ReadOnlyDatabase;
import seedu.watson.model.ReadOnlyUserPrefs;
import seedu.watson.model.UserPrefs;

/**
 * Manages storage of Database data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final DatabaseStorage databaseStorage;
    private final UserPrefsStorage userPrefsStorage;

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
    public Path getDatabaseFilePath() {
        return databaseStorage.getDatabaseFilePath();
    }

    @Override
    public Optional<ReadOnlyDatabase> readDatabase() throws DataConversionException, IOException {
        return readDatabase(databaseStorage.getDatabaseFilePath());
    }

    @Override
    public Optional<ReadOnlyDatabase> readDatabase(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return databaseStorage.readDatabase(filePath);
    }

    @Override
    public void saveDatabase(ReadOnlyDatabase database) throws IOException {
        saveDatabase(database, databaseStorage.getDatabaseFilePath());
    }

    @Override
    public void saveDatabase(ReadOnlyDatabase database, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        databaseStorage.saveDatabase(database, filePath);
    }

}
