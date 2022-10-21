package seedu.studmap.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.studmap.commons.core.LogsCenter;
import seedu.studmap.commons.exceptions.DataConversionException;
import seedu.studmap.commons.exceptions.IllegalValueException;
import seedu.studmap.commons.util.FileUtil;
import seedu.studmap.commons.util.JsonUtil;
import seedu.studmap.model.ReadOnlyStudMap;

/**
 * A class to access StudMap data stored as a json file on the hard disk.
 */
public class JsonStudMapStorage implements StudMapStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonStudMapStorage.class);

    private Path filePath;

    public JsonStudMapStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getStudMapFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyStudMap> readStudMap() throws DataConversionException {
        return readStudMap(filePath);
    }

    /**
     * Similar to {@link #readStudMap()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyStudMap> readStudMap(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableStudMap> jsonStudMap = JsonUtil.readJsonFile(
                filePath, JsonSerializableStudMap.class);
        if (!jsonStudMap.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonStudMap.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveStudMap(ReadOnlyStudMap studMap) throws IOException {
        saveStudMap(studMap, filePath);
    }

    /**
     * Similar to {@link #saveStudMap(ReadOnlyStudMap)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveStudMap(ReadOnlyStudMap studMap, Path filePath) throws IOException {
        requireNonNull(studMap);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableStudMap(studMap), filePath);
    }

}
