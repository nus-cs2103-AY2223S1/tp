package seedu.pennywise.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.pennywise.commons.core.LogsCenter;
import seedu.pennywise.commons.exceptions.DataConversionException;
import seedu.pennywise.commons.exceptions.IllegalValueException;
import seedu.pennywise.commons.util.FileUtil;
import seedu.pennywise.commons.util.JsonUtil;
import seedu.pennywise.model.ReadOnlyPennyWise;

/**
 * A class to access PennyWise data stored as a JSON file on the hard disk.
 */
public class JsonPennyWiseStorage implements PennyWiseStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPennyWiseStorage.class);

    private Path filePath;

    public JsonPennyWiseStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPennyWiseFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPennyWise> readPennyWise() throws DataConversionException {
        return readPennyWise(filePath);
    }

    /**
     * Similar to {@link #readPennyWise()}.
     *
     * @param filePath Non-null location of the data.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPennyWise> readPennyWise(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePennyWise> jsonPennyWise = JsonUtil.readJsonFile(
            filePath, JsonSerializablePennyWise.class);
        if (jsonPennyWise.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPennyWise.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePennyWise(ReadOnlyPennyWise pennyWise) throws IOException {
        savePennyWise(pennyWise, filePath);
    }

    /**
     * Similar to {@link #savePennyWise(ReadOnlyPennyWise)}.
     *
     * @param filePath Non-null location of the data.
     */
    public void savePennyWise(ReadOnlyPennyWise pennyWise, Path filePath) throws IOException {
        requireNonNull(pennyWise);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePennyWise(pennyWise), filePath);
    }

}
