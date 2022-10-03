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

public class JsonTrackOStorage implements TrackOStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTrackOStorage.class);

    private Path ordersFilePath;

    public JsonTrackOStorage(Path ordersFilePath) {
        this.ordersFilePath = ordersFilePath;
    }

    public Path getOrdersFilePath() {
        return ordersFilePath;
    }

    @Override
    public Optional<ReadOnlyTrackO> readTrackO() throws DataConversionException, IOException {
        return readTrackO(ordersFilePath);
    }

    /**
     * Similar to {@link #readTrackO()}
     *
     * @param ordersFilePath Location of the orders data file. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyTrackO> readTrackO(Path ordersFilePath) throws DataConversionException {
        requireNonNull(ordersFilePath);

        Optional<JsonSerializableTrackO> jsonTrackO = JsonUtil.readJsonFile(
                ordersFilePath, JsonSerializableTrackO.class);

        if (!jsonTrackO.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTrackO.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + ordersFilePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTrackO(ReadOnlyTrackO trackO) throws IOException {
        saveTrackO(trackO, ordersFilePath);
    }

    /**
     * Similar to {@link #saveTrackO(ReadOnlyTrackO)}.
     *
     * @param ordersFilePath Location of the orders data file. Cannot be null.
     */
    public void saveTrackO(ReadOnlyTrackO trackO, Path ordersFilePath) throws IOException {
        requireNonNull(trackO);
        requireNonNull(ordersFilePath);

        FileUtil.createIfMissing(ordersFilePath);
        JsonUtil.saveJsonFile(new JsonSerializableTrackO(trackO), ordersFilePath);
    }
}
