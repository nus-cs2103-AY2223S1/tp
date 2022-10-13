package seedu.intrack.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.intrack.commons.core.LogsCenter;
import seedu.intrack.commons.exceptions.DataConversionException;
import seedu.intrack.commons.exceptions.IllegalValueException;
import seedu.intrack.commons.util.FileUtil;
import seedu.intrack.commons.util.JsonUtil;
import seedu.intrack.model.ReadOnlyInTrack;

/**
 * A class to access InTrack data stored as a json file on the hard disk.
 */
public class JsonInTrackStorage implements InTrackStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonInTrackStorage.class);

    private Path filePath;

    public JsonInTrackStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getInTrackFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyInTrack> readInTrack() throws DataConversionException {
        return readInTrack(filePath);
    }

    /**
     * Similar to {@link #readInTrack()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyInTrack> readInTrack(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableInTrack> jsonInTrack = JsonUtil.readJsonFile(
                filePath, JsonSerializableInTrack.class);
        if (!jsonInTrack.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonInTrack.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveInTrack(ReadOnlyInTrack inTrack) throws IOException {
        saveInTrack(inTrack, filePath);
    }

    /**
     * Similar to {@link #saveInTrack(ReadOnlyInTrack)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInTrack(ReadOnlyInTrack inTrack, Path filePath) throws IOException {
        requireNonNull(inTrack);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableInTrack(inTrack), filePath);
    }

}
