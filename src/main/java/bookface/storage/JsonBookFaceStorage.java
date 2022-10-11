package bookface.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import bookface.commons.core.LogsCenter;
import bookface.commons.exceptions.DataConversionException;
import bookface.commons.exceptions.IllegalValueException;
import bookface.commons.util.FileUtil;
import bookface.commons.util.JsonUtil;
import bookface.model.ReadOnlyBookFace;

/**
 * A class to access BookFace data stored as a json file on the hard disk.
 */
public class JsonBookFaceStorage implements BookFaceStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonBookFaceStorage.class);

    private final Path filePath;

    public JsonBookFaceStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getBookFaceFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBookFace> readBookFace() throws DataConversionException {
        return readBookFace(filePath);
    }

    /**
     * Similar to {@link #readBookFace()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyBookFace> readBookFace(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableBookFace> jsonBookFace = JsonUtil.readJsonFile(
                filePath, JsonSerializableBookFace.class);
        if (jsonBookFace.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonBookFace.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveBookFace(ReadOnlyBookFace bookFace) throws IOException {
        saveBookFace(bookFace, filePath);
    }

    /**
     * Similar to {@link #saveBookFace(ReadOnlyBookFace)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveBookFace(ReadOnlyBookFace bookFace, Path filePath) throws IOException {
        requireNonNull(bookFace);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBookFace(bookFace), filePath);
    }

}
