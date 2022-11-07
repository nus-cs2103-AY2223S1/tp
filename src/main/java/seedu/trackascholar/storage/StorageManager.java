package seedu.trackascholar.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.trackascholar.commons.core.LogsCenter;
import seedu.trackascholar.commons.exceptions.DataConversionException;
import seedu.trackascholar.model.ReadOnlyTrackAScholar;
import seedu.trackascholar.model.ReadOnlyUserPrefs;
import seedu.trackascholar.model.UserPrefs;

/**
 * Manages storage of TrackAScholar data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TrackAScholarStorage trackAScholarStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TrackAScholarStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TrackAScholarStorage trackAScholarStorage, UserPrefsStorage userPrefsStorage) {
        this.trackAScholarStorage = trackAScholarStorage;
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


    // ================ TrackAScholar methods ==============================

    @Override
    public Path getTrackAScholarFilePath() {
        return trackAScholarStorage.getTrackAScholarFilePath();
    }

    @Override
    public Optional<ReadOnlyTrackAScholar> readTrackAScholar() throws DataConversionException, IOException {
        return readTrackAScholar(trackAScholarStorage.getTrackAScholarFilePath());
    }

    @Override
    public Optional<ReadOnlyTrackAScholar> readTrackAScholar(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return trackAScholarStorage.readTrackAScholar(filePath);
    }

    @Override
    public void saveTrackAScholar(ReadOnlyTrackAScholar trackAScholar) throws IOException {
        saveTrackAScholar(trackAScholar, trackAScholarStorage.getTrackAScholarFilePath());
    }

    @Override
    public void saveTrackAScholar(ReadOnlyTrackAScholar trackAScholar, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        trackAScholarStorage.saveTrackAScholar(trackAScholar, filePath);
    }

}
