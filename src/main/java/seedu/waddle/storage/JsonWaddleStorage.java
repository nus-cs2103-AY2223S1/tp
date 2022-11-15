package seedu.waddle.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.waddle.commons.core.LogsCenter;
import seedu.waddle.commons.exceptions.DataConversionException;
import seedu.waddle.commons.exceptions.IllegalValueException;
import seedu.waddle.commons.util.FileUtil;
import seedu.waddle.commons.util.JsonUtil;
import seedu.waddle.model.ReadOnlyWaddle;

/**
 * A class to access Waddle data stored as a json file on the hard disk.
 */
public class JsonWaddleStorage implements WaddleStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonWaddleStorage.class);

    private Path filePath;

    public JsonWaddleStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getWaddleFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyWaddle> readWaddle() throws DataConversionException {
        return readWaddle(filePath);
    }

    /**
     * Similar to {@link #readWaddle()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyWaddle> readWaddle(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableWaddle> jsonWaddle = JsonUtil.readJsonFile(
                filePath, JsonSerializableWaddle.class);
        if (!jsonWaddle.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonWaddle.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveWaddle(ReadOnlyWaddle waddle) throws IOException {
        saveWaddle(waddle, filePath);
    }

    /**
     * Similar to {@link #saveWaddle(ReadOnlyWaddle)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveWaddle(ReadOnlyWaddle waddle, Path filePath) throws IOException {
        requireNonNull(waddle);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableWaddle(waddle), filePath);
    }

}
