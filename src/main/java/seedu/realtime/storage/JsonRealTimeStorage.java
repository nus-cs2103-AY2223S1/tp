package seedu.realtime.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.realtime.commons.core.LogsCenter;
import seedu.realtime.commons.exceptions.DataConversionException;
import seedu.realtime.commons.exceptions.IllegalValueException;
import seedu.realtime.commons.util.FileUtil;
import seedu.realtime.commons.util.JsonUtil;
import seedu.realtime.model.ReadOnlyRealTime;

/**
 * A class to access realTime data stored as a json file on the hard disk.
 */
public class JsonRealTimeStorage implements RealTimeStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonRealTimeStorage.class);

    private Path filePath;

    public JsonRealTimeStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getRealTimeFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyRealTime> readRealTime() throws DataConversionException {
        return readRealTime(filePath);
    }

    /**
     * Similar to {@link #readRealTime()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyRealTime> readRealTime(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableRealTime> jsonRealTime = JsonUtil.readJsonFile(
                filePath, JsonSerializableRealTime.class);
        if (!jsonRealTime.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonRealTime.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveRealTime(ReadOnlyRealTime realTime) throws IOException {
        saveRealTime(realTime, filePath);
    }

    /**
     * Similar to {@link #saveRealTime(ReadOnlyRealTime)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveRealTime(ReadOnlyRealTime realTime, Path filePath) throws IOException {
        requireNonNull(realTime);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableRealTime(realTime), filePath);
    }

}
