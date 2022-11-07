package jeryl.fyp.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import jeryl.fyp.commons.core.LogsCenter;
import jeryl.fyp.commons.exceptions.DataConversionException;
import jeryl.fyp.commons.exceptions.IllegalValueException;
import jeryl.fyp.commons.util.FileUtil;
import jeryl.fyp.commons.util.JsonUtil;
import jeryl.fyp.model.ReadOnlyFypManager;

/**
 * A class to access FypManager data stored as a json file on the hard disk.
 */
public class JsonFypManagerStorage implements FypManagerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFypManagerStorage.class);

    private Path filePath;

    public JsonFypManagerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFypManagerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFypManager> readFypManager() throws DataConversionException {
        return readFypManager(filePath);
    }

    /**
     * Similar to {@link #readFypManager()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFypManager> readFypManager(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFypManager> jsonFypManager = JsonUtil.readJsonFile(
                filePath, JsonSerializableFypManager.class);
        if (!jsonFypManager.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFypManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFypManager(ReadOnlyFypManager fypManager) throws IOException {
        saveFypManager(fypManager, filePath);
    }

    /**
     * Similar to {@link #saveFypManager(ReadOnlyFypManager)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFypManager(ReadOnlyFypManager fypManager, Path filePath) throws IOException {
        requireNonNull(fypManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFypManager(fypManager), filePath);
    }

}
