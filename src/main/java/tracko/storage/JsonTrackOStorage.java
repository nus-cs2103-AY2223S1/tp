package tracko.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import tracko.commons.core.LogsCenter;
import tracko.commons.exceptions.DataConversionException;
import tracko.commons.exceptions.IllegalValueException;
import tracko.commons.util.FileUtil;
import tracko.commons.util.JsonUtil;
import tracko.model.ReadOnlyTrackO;

/**
 * A class to access TrackO data stored as json file(s) on the hard disk.
 */
public class JsonTrackOStorage implements TrackOStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTrackOStorage.class);

    private Path trackOFilePath;

    public JsonTrackOStorage(Path trackOFilePath) {
        this.trackOFilePath = trackOFilePath;
    }

    public Path getTrackOFilePath() {
        return trackOFilePath;
    }

    @Override
    public Optional<ReadOnlyTrackO> readTrackO() throws DataConversionException, IOException {
        return readTrackO(trackOFilePath);
    }

    /**
     * Similar to {@link #readTrackO()}
     *
     * @param trackOFilePath Location of the orders data file. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyTrackO> readTrackO(Path trackOFilePath) throws DataConversionException {
        requireNonNull(trackOFilePath);

        Optional<JsonSerializableTrackO> jsonTrackO = JsonUtil.readJsonFile(
                trackOFilePath, JsonSerializableTrackO.class);

        if (!jsonTrackO.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTrackO.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + trackOFilePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTrackO(ReadOnlyTrackO trackO) throws IOException {
        saveTrackO(trackO, trackOFilePath);
    }

    /**
     * Similar to {@link #saveTrackO(ReadOnlyTrackO)}.
     *
     * @param trackOFilePath Location of the orders data file. Cannot be null.
     */
    public void saveTrackO(ReadOnlyTrackO trackO, Path trackOFilePath) throws IOException {
        requireNonNull(trackO);
        requireNonNull(trackOFilePath);

        FileUtil.createIfMissing(trackOFilePath);
        JsonUtil.saveJsonFile(new JsonSerializableTrackO(trackO), trackOFilePath);
    }
}
