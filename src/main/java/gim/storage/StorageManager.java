package gim.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import gim.commons.core.LogsCenter;
import gim.commons.exceptions.DataConversionException;
import gim.model.ReadOnlyExerciseTracker;
import gim.model.ReadOnlyUserPrefs;
import gim.model.UserPrefs;

/**
 * Manages storage of ExerciseTracker data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ExerciseTrackerStorage exerciseTrackerStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ExerciseTrackerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ExerciseTrackerStorage exerciseTrackerStorage, UserPrefsStorage userPrefsStorage) {
        this.exerciseTrackerStorage = exerciseTrackerStorage;
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


    // ================ ExerciseTracker methods ==============================

    @Override
    public Path getExerciseTrackerFilePath() {
        return exerciseTrackerStorage.getExerciseTrackerFilePath();
    }

    @Override
    public Optional<ReadOnlyExerciseTracker> readExerciseTracker() throws DataConversionException, IOException {
        return readExerciseTracker(exerciseTrackerStorage.getExerciseTrackerFilePath());
    }

    @Override
    public Optional<ReadOnlyExerciseTracker> readExerciseTracker(Path filePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return exerciseTrackerStorage.readExerciseTracker(filePath);
    }

    @Override
    public void saveExerciseTracker(ReadOnlyExerciseTracker exerciseTracker) throws IOException {
        saveExerciseTracker(exerciseTracker, exerciseTrackerStorage.getExerciseTrackerFilePath());
    }

    @Override
    public void saveExerciseTracker(ReadOnlyExerciseTracker exerciseTracker, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        exerciseTrackerStorage.saveExerciseTracker(exerciseTracker, filePath);
    }

}
