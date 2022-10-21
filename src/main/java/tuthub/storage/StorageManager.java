package tuthub.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import tuthub.commons.core.LogsCenter;
import tuthub.commons.exceptions.DataConversionException;
import tuthub.model.ReadOnlyTuthub;
import tuthub.model.ReadOnlyUserPrefs;
import tuthub.model.UserPrefs;

/**
 * Manages storage of Tuthub data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TuthubStorage tuthubStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TuthubStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TuthubStorage tuthubStorage, UserPrefsStorage userPrefsStorage) {
        this.tuthubStorage = tuthubStorage;
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


    // ================ Tuthub methods ==============================

    @Override
    public Path getTuthubFilePath() {
        return tuthubStorage.getTuthubFilePath();
    }

    @Override
    public Optional<ReadOnlyTuthub> readTuthub() throws DataConversionException, IOException {
        return readTuthub(tuthubStorage.getTuthubFilePath());
    }

    @Override
    public Optional<ReadOnlyTuthub> readTuthub(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return tuthubStorage.readTuthub(filePath);
    }

    @Override
    public void saveTuthub(ReadOnlyTuthub tuthub) throws IOException {
        saveTuthub(tuthub, tuthubStorage.getTuthubFilePath());
    }

    @Override
    public void saveTuthub(ReadOnlyTuthub tuthub, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        tuthubStorage.saveTuthub(tuthub, filePath);
    }

}
