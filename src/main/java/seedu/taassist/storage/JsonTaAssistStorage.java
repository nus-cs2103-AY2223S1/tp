package seedu.taassist.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.taassist.commons.core.LogsCenter;
import seedu.taassist.commons.exceptions.DataConversionException;
import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.commons.util.FileUtil;
import seedu.taassist.commons.util.JsonUtil;
import seedu.taassist.model.ReadOnlyTaAssist;

/**
 * A class to access TaAssist data stored as a json file on the hard disk.
 */
public class JsonTaAssistStorage implements TaAssistStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaAssistStorage.class);

    private Path filePath;

    public JsonTaAssistStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaAssistFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaAssist> readTaAssist() throws DataConversionException {
        return readTaAssist(filePath);
    }

    /**
     * Similar to {@link #readTaAssist()}.
     *
     * @param filePath Location of the data. Cannot be null.
     * @throws DataConversionException If the file is not in the correct format.
     */
    public Optional<ReadOnlyTaAssist> readTaAssist(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaAssist> jsonTaAssist = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaAssist.class);
        if (!jsonTaAssist.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaAssist.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTaAssist(ReadOnlyTaAssist taAssist) throws IOException {
        saveTaAssist(taAssist, filePath);
    }

    /**
     * Similar to {@link #saveTaAssist(ReadOnlyTaAssist)}.
     *
     * @param filePath Location of the data. Cannot be null.
     * @throws IOException If there was any problem creating the file or writing to the file.
     */
    public void saveTaAssist(ReadOnlyTaAssist taAssist, Path filePath) throws IOException {
        requireNonNull(taAssist);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaAssist(taAssist), filePath);
    }

}
