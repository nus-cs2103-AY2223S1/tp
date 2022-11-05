package seedu.realtime.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.realtime.commons.core.LogsCenter;
import seedu.realtime.commons.exceptions.DataConversionException;
import seedu.realtime.model.ReadOnlyRealTime;
import seedu.realtime.model.ReadOnlyUserPrefs;
import seedu.realtime.model.UserPrefs;

/**
 * Manages storage of realTime data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private RealTimeStorage realTimeStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code RealTimeStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(RealTimeStorage realTimeStorage, UserPrefsStorage userPrefsStorage) {
        this.realTimeStorage = realTimeStorage;
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


    // ================ realTime methods ==============================

    @Override
    public Path getRealTimeFilePath() {
        return realTimeStorage.getRealTimeFilePath();
    }

    @Override
    public Optional<ReadOnlyRealTime> readRealTime() throws DataConversionException, IOException {
        return readRealTime(realTimeStorage.getRealTimeFilePath());
    }

    @Override
    public Optional<ReadOnlyRealTime> readRealTime(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return realTimeStorage.readRealTime(filePath);
    }

    @Override
    public void saveRealTime(ReadOnlyRealTime realTime) throws IOException {
        saveRealTime(realTime, realTimeStorage.getRealTimeFilePath());
    }

    @Override
    public void saveRealTime(ReadOnlyRealTime realTime, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        realTimeStorage.saveRealTime(realTime, filePath);
    }

}
