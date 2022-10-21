package friday.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import friday.commons.core.LogsCenter;
import friday.commons.exceptions.DataConversionException;
import friday.model.ReadOnlyFriday;
import friday.model.ReadOnlyUserPrefs;
import friday.model.UserPrefs;

/**
 * Manages storage of Friday data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FridayStorage fridayStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FridayStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FridayStorage fridayStorage, UserPrefsStorage userPrefsStorage) {
        this.fridayStorage = fridayStorage;
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


    // ================ Friday methods ==============================

    @Override
    public Path getFridayFilePath() {
        return fridayStorage.getFridayFilePath();
    }

    @Override
    public Optional<ReadOnlyFriday> readFriday() throws DataConversionException, IOException {
        return readFriday(fridayStorage.getFridayFilePath());
    }

    @Override
    public Optional<ReadOnlyFriday> readFriday(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return fridayStorage.readFriday(filePath);
    }

    @Override
    public void saveFriday(ReadOnlyFriday friday) throws IOException {
        saveFriday(friday, fridayStorage.getFridayFilePath());
    }

    @Override
    public void saveFriday(ReadOnlyFriday friday, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        fridayStorage.saveFriday(friday, filePath);
    }

}
