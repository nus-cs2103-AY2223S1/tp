package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFindMyIntern;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of FindMyIntern data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FindMyInternStorage findMyInternStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FindMyInternStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FindMyInternStorage findMyInternStorage, UserPrefsStorage userPrefsStorage) {
        this.findMyInternStorage = findMyInternStorage;
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


    // ================ FindMyIntern methods ==============================

    @Override
    public Path getFindMyInternFilePath() {
        return findMyInternStorage.getFindMyInternFilePath();
    }

    @Override
    public Optional<ReadOnlyFindMyIntern> readFindMyIntern() throws DataConversionException, IOException {
        return readFindMyIntern(findMyInternStorage.getFindMyInternFilePath());
    }

    @Override
    public Optional<ReadOnlyFindMyIntern> readFindMyIntern(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return findMyInternStorage.readFindMyIntern(filePath);
    }

    @Override
    public void saveFindMyIntern(ReadOnlyFindMyIntern findMyIntern) throws IOException {
        saveFindMyIntern(findMyIntern, findMyInternStorage.getFindMyInternFilePath());
    }

    @Override
    public void saveFindMyIntern(ReadOnlyFindMyIntern findMyIntern, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        findMyInternStorage.saveFindMyIntern(findMyIntern, filePath);
    }

}
