package tuthub.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import tuthub.commons.core.LogsCenter;
import tuthub.commons.exceptions.DataConversionException;
import tuthub.commons.exceptions.IllegalValueException;
import tuthub.commons.util.FileUtil;
import tuthub.commons.util.JsonUtil;
import tuthub.model.ReadOnlyTuthub;

/**
 * A class to access Tuthub data stored as a json file on the hard disk.
 */
public class JsonTuthubStorage implements TuthubStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTuthubStorage.class);

    private Path filePath;

    public JsonTuthubStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTuthubFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTuthub> readTuthub() throws DataConversionException {
        return readTuthub(filePath);
    }

    /**
     * Similar to {@link #readTuthub()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTuthub> readTuthub(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTuthub> jsonTuthub = JsonUtil.readJsonFile(
                filePath, JsonSerializableTuthub.class);
        if (!jsonTuthub.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTuthub.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTuthub(ReadOnlyTuthub tuthub) throws IOException {
        saveTuthub(tuthub, filePath);
    }

    /**
     * Similar to {@link #saveTuthub(ReadOnlyTuthub)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTuthub(ReadOnlyTuthub tuthub, Path filePath) throws IOException {
        requireNonNull(tuthub);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTuthub(tuthub), filePath);
    }

}
