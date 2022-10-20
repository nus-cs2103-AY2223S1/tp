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
import seedu.condonery.model.client.ReadOnlyClientDirectory;

/**
 * A class to access ClientDirectory data stored as a json file on the hard disk.
 */
public class JsonClientDirectoryStorage implements ClientDirectoryStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonClientDirectoryStorage.class);

    private Path filePath;

    public JsonClientDirectoryStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getClientDirectoryFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyClientDirectory> readClientDirectory() throws DataConversionException {
        return readClientDirectory(filePath);
    }

    /**
     * Similar to {@link #readClientDirectory()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyClientDirectory> readClientDirectory(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableClientDirectory> jsonClientDirectory = JsonUtil.readJsonFile(
                filePath, JsonSerializableClientDirectory.class);
        if (!jsonClientDirectory.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonClientDirectory.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveClientDirectory(ReadOnlyClientDirectory clientDirectory) throws IOException {
        saveClientDirectory(clientDirectory, filePath);
    }

    /**
     * Similar to {@link #saveClientDirectory(ReadOnlyClientDirectory)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveClientDirectory(ReadOnlyClientDirectory clientDirectory, Path filePath) throws IOException {
        requireNonNull(clientDirectory);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableClientDirectory(clientDirectory), filePath);
    }

}
