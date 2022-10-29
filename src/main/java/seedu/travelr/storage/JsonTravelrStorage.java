package seedu.travelr.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.travelr.commons.core.LogsCenter;
import seedu.travelr.commons.exceptions.DataConversionException;
import seedu.travelr.commons.exceptions.IllegalValueException;
import seedu.travelr.commons.util.FileUtil;
import seedu.travelr.commons.util.JsonUtil;
import seedu.travelr.model.ReadOnlyTravelr;

/**
 * A class to access Travelr data stored as a json file on the hard disk.
 */
public class JsonTravelrStorage implements TravelrStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTravelrStorage.class);

    private Path filePath;

    public JsonTravelrStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTravelrFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTravelr> readTravelr() throws DataConversionException {
        return readTravelr(filePath);
    }

    /**
     * Similar to {@link #readTravelr()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTravelr> readTravelr(Path filePath) throws DataConversionException {
        // No problems here for storage
        requireNonNull(filePath);

        Optional<JsonSerializableTravelr> jsonTravelr = JsonUtil.readJsonFile(
                filePath, JsonSerializableTravelr.class);
        if (!jsonTravelr.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTravelr.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTravelr(ReadOnlyTravelr travelr) throws IOException {
        saveTravelr(travelr, filePath);
    }

    /**
     * Similar to {@link #saveTravelr(ReadOnlyTravelr)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTravelr(ReadOnlyTravelr travelr, Path filePath) throws IOException {
        requireNonNull(travelr);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTravelr(travelr), filePath);
    }

}
