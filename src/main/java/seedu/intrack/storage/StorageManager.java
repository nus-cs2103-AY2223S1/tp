package seedu.intrack.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.intrack.commons.core.LogsCenter;
import seedu.intrack.commons.exceptions.DataConversionException;
import seedu.intrack.model.ReadOnlyInTrack;
import seedu.intrack.model.ReadOnlyUserPrefs;
import seedu.intrack.model.UserPrefs;

/**
 * Manages storage of InTrack data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private InTrackStorage inTrackStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code InTrackStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(InTrackStorage inTrackStorage, UserPrefsStorage userPrefsStorage) {
        this.inTrackStorage = inTrackStorage;
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


    // ================ InTrack methods ==============================

    @Override
    public Path getInTrackFilePath() {
        return inTrackStorage.getInTrackFilePath();
    }

    @Override
    public Optional<ReadOnlyInTrack> readInTrack() throws DataConversionException, IOException {
        return readInTrack(inTrackStorage.getInTrackFilePath());
    }

    @Override
    public Optional<ReadOnlyInTrack> readInTrack(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return inTrackStorage.readInTrack(filePath);
    }

    @Override
    public void saveInTrack(ReadOnlyInTrack inTrack) throws IOException {
        saveInTrack(inTrack, inTrackStorage.getInTrackFilePath());
    }

    @Override
    public void saveInTrack(ReadOnlyInTrack inTrack, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        inTrackStorage.saveInTrack(inTrack, filePath);
    }

}
