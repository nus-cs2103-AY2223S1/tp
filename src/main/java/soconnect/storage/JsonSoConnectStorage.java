package soconnect.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import soconnect.commons.core.LogsCenter;
import soconnect.commons.exceptions.DataConversionException;
import soconnect.commons.exceptions.IllegalValueException;
import soconnect.commons.util.FileUtil;
import soconnect.commons.util.JsonUtil;
import soconnect.model.ReadOnlySoConnect;

/**
 * A class to access SoConnect data stored as a json file on the hard disk.
 */
public class JsonSoConnectStorage implements SoConnectStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSoConnectStorage.class);

    private Path filePath;

    public JsonSoConnectStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSoConnectFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySoConnect> readSoConnect() throws DataConversionException {
        return readSoConnect(filePath);
    }

    /**
     * Similar to {@link #readSoConnect()}.
     *
     * @param filePath Location of the data. Cannot be null.
     * @throws DataConversionException If the file is not in the correct format.
     */
    public Optional<ReadOnlySoConnect> readSoConnect(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSoConnect> jsonSoConnect = JsonUtil.readJsonFile(
                filePath, JsonSerializableSoConnect.class);
        if (!jsonSoConnect.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSoConnect.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSoConnect(ReadOnlySoConnect soConnect) throws IOException {
        saveSoConnect(soConnect, filePath);
    }

    /**
     * Similar to {@link #saveSoConnect(ReadOnlySoConnect)}.
     *
     * @param filePath Location of the data. Cannot be null.
     */
    public void saveSoConnect(ReadOnlySoConnect soConnect, Path filePath) throws IOException {
        requireNonNull(soConnect);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSoConnect(soConnect), filePath);
    }

}
