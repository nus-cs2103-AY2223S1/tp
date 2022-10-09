package gim.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import gim.commons.exceptions.DataConversionException;
import gim.model.ReadOnlyExerciseTracker;

/**
 * Represents a storage for {@link gim.model.ExerciseTracker}.
 */
public interface ExerciseTrackerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getExerciseTrackerFilePath();

    /**
     * Returns ExerciseTracker data as a {@link ReadOnlyExerciseTracker}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyExerciseTracker> readExerciseTracker() throws DataConversionException, IOException;

    /**
     * @see #getExerciseTrackerFilePath()
     */
    Optional<ReadOnlyExerciseTracker> readExerciseTracker(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyExerciseTracker} to the storage.
     * @param exerciseTracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveExerciseTracker(ReadOnlyExerciseTracker exerciseTracker) throws IOException;

    /**
     * @see #saveExerciseTracker(ReadOnlyExerciseTracker)
     */
    void saveExerciseTracker(ReadOnlyExerciseTracker exerciseTracker, Path filePath) throws IOException;

}
