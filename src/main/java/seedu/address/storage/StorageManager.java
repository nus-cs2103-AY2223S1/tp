package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyNuScheduler;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of NuScheduler data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private NuSchedulerStorage nuSchedulerStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code NuSchedulerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(NuSchedulerStorage nuSchedulerStorage, UserPrefsStorage userPrefsStorage) {
        this.nuSchedulerStorage = nuSchedulerStorage;
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


    // ================ NuScheduler methods ==============================

    @Override
    public Path getNuSchedulerFilePath() {
        return nuSchedulerStorage.getNuSchedulerFilePath();
    }

    @Override
    public Optional<ReadOnlyNuScheduler> readNuScheduler() throws DataConversionException, IOException {
        return readNuScheduler(nuSchedulerStorage.getNuSchedulerFilePath());
    }

    @Override
    public Optional<ReadOnlyNuScheduler> readNuScheduler(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return nuSchedulerStorage.readNuScheduler(filePath);
    }

    @Override
    public void saveNuScheduler(ReadOnlyNuScheduler nuScheduler) throws IOException {
        saveNuScheduler(nuScheduler, nuSchedulerStorage.getNuSchedulerFilePath());
    }

    @Override
    public void saveNuScheduler(ReadOnlyNuScheduler nuScheduler, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        nuSchedulerStorage.saveNuScheduler(nuScheduler, filePath);
    }

}
