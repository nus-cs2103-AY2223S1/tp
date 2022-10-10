package gim.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import gim.commons.core.LogsCenter;
import gim.commons.exceptions.DataConversionException;
import gim.commons.exceptions.IllegalValueException;
import gim.commons.util.FileUtil;
import gim.commons.util.JsonUtil;
import gim.model.ReadOnlyExerciseTracker;

/**
 * A class to access ExerciseTracker data stored as a json file on the hard disk.
 */
public class JsonExerciseTrackerStorage implements ExerciseTrackerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonExerciseTrackerStorage.class);

    private Path filePath;

    public JsonExerciseTrackerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getExerciseTrackerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyExerciseTracker> readExerciseTracker() throws DataConversionException {
        return readExerciseTracker(filePath);
    }

    /**
     * Similar to {@link #readExerciseTracker()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyExerciseTracker> readExerciseTracker(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableExerciseTracker> jsonExerciseTracker = JsonUtil.readJsonFile(
                filePath, JsonSerializableExerciseTracker.class);
        if (!jsonExerciseTracker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonExerciseTracker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveExerciseTracker(ReadOnlyExerciseTracker exerciseTracker) throws IOException {
        saveExerciseTracker(exerciseTracker, filePath);
    }

    /**
     * Similar to {@link #saveExerciseTracker(ReadOnlyExerciseTracker)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveExerciseTracker(ReadOnlyExerciseTracker exerciseTracker, Path filePath) throws IOException {
        requireNonNull(exerciseTracker);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableExerciseTracker(exerciseTracker), filePath);
    }

}
