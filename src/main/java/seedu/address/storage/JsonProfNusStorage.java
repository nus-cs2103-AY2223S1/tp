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
import seedu.address.model.ReadOnlyProfNus;

/**
 * A class to access ProfNus data stored as a json file on the hard disk.
 */
public class JsonProfNusStorage implements ProfNusStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonProfNusStorage.class);

    private Path filePath;

    public JsonProfNusStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getProfNusFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyProfNus> readProfNus() throws DataConversionException {
        return readProfNus(filePath);
    }

    /**
     * Similar to {@link #readProfNus()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyProfNus> readProfNus(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableProfNus> jsonProfNus = JsonUtil.readJsonFile(
                filePath, JsonSerializableProfNus.class);
        if (!jsonProfNus.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonProfNus.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveProfNus(ReadOnlyProfNus profNus) throws IOException {
        saveProfNus(profNus, filePath);
    }

    /**
     * Similar to {@link #saveProfNus(ReadOnlyProfNus)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveProfNus(ReadOnlyProfNus profNus, Path filePath) throws IOException {
        requireNonNull(profNus);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableProfNus(profNus), filePath);
    }

}
