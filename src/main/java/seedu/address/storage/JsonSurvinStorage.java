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
import seedu.address.model.ReadOnlySurvin;

/**
 * A class to access Survin data stored as a json file on the hard disk.
 */
public class JsonSurvinStorage implements SurvinStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSurvinStorage.class);

    private Path filePath;

    public JsonSurvinStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSurvinFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySurvin> readSurvin() throws DataConversionException {
        return readSurvin(filePath);
    }

    /**
     * Similar to {@link #readSurvin()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlySurvin> readSurvin(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSurvin> jsonSurvin = JsonUtil.readJsonFile(
                filePath, JsonSerializableSurvin.class);
        if (!jsonSurvin.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSurvin.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSurvin(ReadOnlySurvin survin) throws IOException {
        saveSurvin(survin, filePath);
    }

    /**
     * Similar to {@link #saveSurvin(ReadOnlySurvin)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveSurvin(ReadOnlySurvin survin, Path filePath) throws IOException {
        requireNonNull(survin);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSurvin(survin), filePath);
    }

}
