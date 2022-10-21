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
import seedu.address.model.ReadOnlyNuScheduler;

/**
 * A class to access NuScheduler data stored as a json file on the hard disk.
 */
public class JsonNuSchedulerStorage implements NuSchedulerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonNuSchedulerStorage.class);

    private Path filePath;

    public JsonNuSchedulerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getNuSchedulerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyNuScheduler> readNuScheduler() throws DataConversionException {
        return readNuScheduler(filePath);
    }

    /**
     * Similar to {@link #readNuScheduler()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyNuScheduler> readNuScheduler(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableNuScheduler> jsonNuScheduler = JsonUtil.readJsonFile(
                filePath, JsonSerializableNuScheduler.class);
        if (!jsonNuScheduler.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonNuScheduler.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveNuScheduler(ReadOnlyNuScheduler nuScheduler) throws IOException {
        saveNuScheduler(nuScheduler, filePath);
    }

    /**
     * Similar to {@link #saveNuScheduler(ReadOnlyNuScheduler)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveNuScheduler(ReadOnlyNuScheduler nuScheduler, Path filePath) throws IOException {
        requireNonNull(nuScheduler);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableNuScheduler(nuScheduler), filePath);
    }

}
