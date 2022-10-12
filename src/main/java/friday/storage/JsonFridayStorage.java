package friday.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import friday.commons.core.LogsCenter;
import friday.commons.exceptions.DataConversionException;
import friday.commons.exceptions.IllegalValueException;
import friday.commons.util.FileUtil;
import friday.commons.util.JsonUtil;
import friday.model.ReadOnlyFriday;

/**
 * A class to access Friday data stored as a json file on the hard disk.
 */
public class JsonFridayStorage implements FridayStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFridayStorage.class);

    private Path filePath;

    public JsonFridayStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFridayFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFriday> readFriday() throws DataConversionException {
        return readFriday(filePath);
    }

    /**
     * Similar to {@link #readFriday()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFriday> readFriday(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFriday> jsonFriday = JsonUtil.readJsonFile(
                filePath, JsonSerializableFriday.class);
        if (!jsonFriday.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFriday.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFriday(ReadOnlyFriday friday) throws IOException {
        saveFriday(friday, filePath);
    }

    /**
     * Similar to {@link #saveFriday(ReadOnlyFriday)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFriday(ReadOnlyFriday friday, Path filePath) throws IOException {
        requireNonNull(friday);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFriday(friday), filePath);
    }

}
