package gim.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import gim.commons.exceptions.DataConversionException;
import gim.model.ReadOnlyExerciseTracker;
import gim.model.ReadOnlyUserPrefs;
import gim.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ExerciseTrackerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getExerciseTrackerFilePath();

    @Override
    Optional<ReadOnlyExerciseTracker> readExerciseTracker() throws DataConversionException, IOException;

    @Override
    void saveExerciseTracker(ReadOnlyExerciseTracker exerciseTracker) throws IOException;

}
