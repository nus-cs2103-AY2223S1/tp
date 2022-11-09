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
import seedu.address.model.ReadOnlyMyInsuRec;

/**
 * A class to access MyInsuRec data stored as a json file on the hard disk.
 */
public class JsonMyInsuRecStorage implements MyInsuRecStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMyInsuRecStorage.class);

    private Path filePath;

    public JsonMyInsuRecStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMyInsuRecFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMyInsuRec> readMyInsuRec() throws DataConversionException {
        return readMyInsuRec(filePath);
    }

    /**
     * Similar to {@link #readMyInsuRec()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMyInsuRec> readMyInsuRec(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableMyInsuRec> jsonMyInsuRec = JsonUtil.readJsonFile(
                filePath, JsonSerializableMyInsuRec.class);
        if (!jsonMyInsuRec.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMyInsuRec.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMyInsuRec(ReadOnlyMyInsuRec myInsuRec) throws IOException {
        saveMyInsuRec(myInsuRec, filePath);
    }

    /**
     * Similar to {@link #saveMyInsuRec(ReadOnlyMyInsuRec)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMyInsuRec(ReadOnlyMyInsuRec myInsuRec, Path filePath) throws IOException {
        requireNonNull(myInsuRec);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMyInsuRec(myInsuRec), filePath);
    }

}
