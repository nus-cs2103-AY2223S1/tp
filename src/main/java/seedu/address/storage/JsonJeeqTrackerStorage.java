package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyJeeqTracker;

/**
 * A class to access JeeqTracker data stored as a json file on the hard disk.
 */
public class JsonJeeqTrackerStorage implements JeeqTrackerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonJeeqTrackerStorage.class);

    private Path filePath;

    public JsonJeeqTrackerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getJeeqTrackerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyJeeqTracker> readJeeqTracker() throws DataConversionException {
        return readJeeqTracker(filePath);
    }

    /**
     * Similar to {@link #readJeeqTracker()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyJeeqTracker> readJeeqTracker(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableJeeqTracker> jsonJeeqTracker = JsonUtil.readJsonFile(
                filePath, JsonSerializableJeeqTracker.class);
        if (!jsonJeeqTracker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonJeeqTracker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveJeeqTracker(ReadOnlyJeeqTracker jeeqTracker) throws IOException {
        saveJeeqTracker(jeeqTracker, filePath);
    }

    /**
     * Similar to {@link #saveJeeqTracker(ReadOnlyJeeqTracker)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveJeeqTracker(ReadOnlyJeeqTracker jeeqTracker, Path filePath) throws IOException {
        requireNonNull(jeeqTracker);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableJeeqTracker(jeeqTracker), filePath);
    }

}
