package jarvis.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import jarvis.commons.core.LogsCenter;
import jarvis.commons.exceptions.DataConversionException;
import jarvis.commons.exceptions.IllegalValueException;
import jarvis.commons.util.FileUtil;
import jarvis.commons.util.JsonUtil;
import jarvis.model.ReadOnlyTaskBook;

/**
 * A class to access TaskBook data stored as a json file on the hard disk.
 */
public class JsonTaskBookStorage implements TaskBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaskBookStorage.class);

    private Path filePath;

    public JsonTaskBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaskBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTaskBook() throws DataConversionException {
        return readTaskBook(filePath);
    }

    /**
     * Similar to {@link #readTaskBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTaskBook> readTaskBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskBook> jsonTaskBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskBook.class);
        if (!jsonTaskBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTaskBook(ReadOnlyTaskBook taskBook) throws IOException {
        saveTaskBook(taskBook, filePath);
    }

    /**
     * Similar to {@link #saveTaskBook(ReadOnlyTaskBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTaskBook(ReadOnlyTaskBook taskBook, Path filePath) throws IOException {
        requireNonNull(taskBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskBook(taskBook), filePath);
    }

}
