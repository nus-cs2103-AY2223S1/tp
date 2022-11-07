package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlySurvin;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Survin data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SurvinStorage survinStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code SurvinStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(SurvinStorage survinStorage, UserPrefsStorage userPrefsStorage) {
        this.survinStorage = survinStorage;
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


    // ================ Survin methods ==============================

    @Override
    public Path getSurvinFilePath() {
        return survinStorage.getSurvinFilePath();
    }

    @Override
    public Optional<ReadOnlySurvin> readSurvin() throws DataConversionException, IOException {
        return readSurvin(survinStorage.getSurvinFilePath());
    }

    @Override
    public Optional<ReadOnlySurvin> readSurvin(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return survinStorage.readSurvin(filePath);
    }

    @Override
    public void saveSurvin(ReadOnlySurvin survin) throws IOException {
        saveSurvin(survin, survinStorage.getSurvinFilePath());
    }

    @Override
    public void saveSurvin(ReadOnlySurvin survin, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        survinStorage.saveSurvin(survin, filePath);
    }

}
