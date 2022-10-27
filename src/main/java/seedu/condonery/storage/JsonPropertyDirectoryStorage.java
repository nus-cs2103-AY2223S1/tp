package seedu.condonery.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.condonery.commons.core.LogsCenter;
import seedu.condonery.commons.exceptions.DataConversionException;
import seedu.condonery.commons.exceptions.IllegalValueException;
import seedu.condonery.commons.util.FileUtil;
import seedu.condonery.commons.util.JsonUtil;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;

/**
 * A class to access PropertyDirectory data stored as a json file on the hard disk.
 */
public class JsonPropertyDirectoryStorage implements PropertyDirectoryStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPropertyDirectoryStorage.class);

    private Path filePath;

    public JsonPropertyDirectoryStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPropertyDirectoryFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPropertyDirectory> readPropertyDirectory() throws DataConversionException {
        return readPropertyDirectory(filePath);
    }

    /**
     * Similar to {@link #readPropertyDirectory()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPropertyDirectory> readPropertyDirectory(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePropertyDirectory> jsonPropertyDirectory = JsonUtil.readJsonFile(
                filePath, JsonSerializablePropertyDirectory.class);
        if (!jsonPropertyDirectory.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPropertyDirectory.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePropertyDirectory(ReadOnlyPropertyDirectory propertyDirectory) throws IOException {
        savePropertyDirectory(propertyDirectory, filePath);
    }

    /**
     * Similar to {@link #savePropertyDirectory(ReadOnlyPropertyDirectory)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePropertyDirectory(ReadOnlyPropertyDirectory propertyDirectory, Path filePath) throws IOException {
        requireNonNull(propertyDirectory);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePropertyDirectory(propertyDirectory), filePath);
    }

}
