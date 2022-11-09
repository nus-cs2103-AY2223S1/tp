package seedu.nutrigoals.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.nutrigoals.commons.core.LogsCenter;
import seedu.nutrigoals.commons.exceptions.DataConversionException;
import seedu.nutrigoals.commons.exceptions.IllegalValueException;
import seedu.nutrigoals.commons.util.FileUtil;
import seedu.nutrigoals.commons.util.JsonUtil;
import seedu.nutrigoals.model.ReadOnlyNutriGoals;

/**
 * A class to access NutriGoals data stored as a json file on the hard disk.
 */
public class JsonNutriGoalsStorage implements NutriGoalsStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonNutriGoalsStorage.class);

    private Path filePath;

    public JsonNutriGoalsStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getNutriGoalsFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyNutriGoals> readNutriGoals() throws DataConversionException {
        return readNutriGoals(filePath);
    }

    /**
     * Similar to {@link #readNutriGoals()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyNutriGoals> readNutriGoals(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableNutriGoals> jsonNutriGoals = JsonUtil.readJsonFile(
                filePath, JsonSerializableNutriGoals.class);
        if (!jsonNutriGoals.isPresent()) {
            return Optional.empty();
        }

        try {
            Optional<ReadOnlyNutriGoals> output = Optional.of(jsonNutriGoals.get().toModelType());
            return output;
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveNutriGoals(ReadOnlyNutriGoals nutriGoals) throws IOException {
        saveNutriGoals(nutriGoals, filePath);
    }

    /**
     * Similar to {@link #saveNutriGoals(ReadOnlyNutriGoals)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveNutriGoals(ReadOnlyNutriGoals nutriGoals, Path filePath) throws IOException {
        requireNonNull(nutriGoals);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableNutriGoals(nutriGoals), filePath);
    }

}
