package seedu.travelr.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.travelr.commons.core.LogsCenter;
import seedu.travelr.commons.exceptions.DataConversionException;
import seedu.travelr.model.ReadOnlyTravelr;
import seedu.travelr.model.ReadOnlyUserPrefs;
import seedu.travelr.model.UserPrefs;

/**
 * Manages storage of Travelr data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TravelrStorage travelrStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TravelrStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TravelrStorage travelrStorage, UserPrefsStorage userPrefsStorage) {
        this.travelrStorage = travelrStorage;
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


    // ================ Travelr methods ==============================

    @Override
    public Path getTravelrFilePath() {
        return travelrStorage.getTravelrFilePath();
    }

    @Override
    public Optional<ReadOnlyTravelr> readTravelr() throws DataConversionException, IOException {
        return readTravelr(travelrStorage.getTravelrFilePath());
    }

    @Override
    public Optional<ReadOnlyTravelr> readTravelr(Path filePath) throws DataConversionException, IOException {
        // No problems here with storage
        logger.fine("Attempting to read data from file: " + filePath);
        return travelrStorage.readTravelr(filePath);
    }

    @Override
    public void saveTravelr(ReadOnlyTravelr travelr) throws IOException {
        saveTravelr(travelr, travelrStorage.getTravelrFilePath());
    }

    @Override
    public void saveTravelr(ReadOnlyTravelr travelr, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        travelrStorage.saveTravelr(travelr, filePath);
    }

}
