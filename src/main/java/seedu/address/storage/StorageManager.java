package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyJeeqTracker;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of JeeqTracker data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private JeeqTrackerStorage jeeqTrackerStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code JeeqTrackerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(JeeqTrackerStorage jeeqTrackerStorage, UserPrefsStorage userPrefsStorage) {
        this.jeeqTrackerStorage = jeeqTrackerStorage;
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


    // ================ JeeqTracker methods ==============================

    @Override
    public Path getJeeqTrackerFilePath() {
        return jeeqTrackerStorage.getJeeqTrackerFilePath();
    }

    @Override
    public Optional<ReadOnlyJeeqTracker> readJeeqTracker() throws DataConversionException, IOException {
        return readJeeqTracker(jeeqTrackerStorage.getJeeqTrackerFilePath());
    }

    @Override
    public Optional<ReadOnlyJeeqTracker> readJeeqTracker(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return jeeqTrackerStorage.readJeeqTracker(filePath);
    }

    @Override
    public void saveJeeqTracker(ReadOnlyJeeqTracker jeeqTracker) throws IOException {
        saveJeeqTracker(jeeqTracker, jeeqTrackerStorage.getJeeqTrackerFilePath());
    }

    @Override
    public void saveJeeqTracker(ReadOnlyJeeqTracker jeeqTracker, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        jeeqTrackerStorage.saveJeeqTracker(jeeqTracker, filePath);
    }

}
