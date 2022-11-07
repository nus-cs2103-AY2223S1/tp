package jeryl.fyp.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import jeryl.fyp.commons.core.LogsCenter;
import jeryl.fyp.commons.exceptions.DataConversionException;
import jeryl.fyp.model.ReadOnlyFypManager;
import jeryl.fyp.model.ReadOnlyUserPrefs;
import jeryl.fyp.model.UserPrefs;

/**
 * Manages storage of FypManager data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FypManagerStorage fypManagerStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FypManagerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FypManagerStorage fypManagerStorage, UserPrefsStorage userPrefsStorage) {
        this.fypManagerStorage = fypManagerStorage;
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


    // ================ FypManager methods ==============================

    @Override
    public Path getFypManagerFilePath() {
        return fypManagerStorage.getFypManagerFilePath();
    }

    @Override
    public Optional<ReadOnlyFypManager> readFypManager() throws DataConversionException, IOException {
        return readFypManager(fypManagerStorage.getFypManagerFilePath());
    }

    @Override
    public Optional<ReadOnlyFypManager> readFypManager(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return fypManagerStorage.readFypManager(filePath);
    }

    @Override
    public void saveFypManager(ReadOnlyFypManager fypManager) throws IOException {
        saveFypManager(fypManager, fypManagerStorage.getFypManagerFilePath());
    }

    @Override
    public void saveFypManager(ReadOnlyFypManager fypManager, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        fypManagerStorage.saveFypManager(fypManager, filePath);
    }

}
