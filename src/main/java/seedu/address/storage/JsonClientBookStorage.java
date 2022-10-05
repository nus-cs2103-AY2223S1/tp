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
import seedu.address.model.ReadOnlyClientBook;

/**
 * A class to access ClientBook data stored as a json file on the hard disk.
 */
public class JsonClientBookStorage implements ClientBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonClientBookStorage.class);

    private Path filePath;

    public JsonClientBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getClientBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyClientBook> readClientBook() throws DataConversionException {
        return readClientBook(filePath);
    }

    /**
     * Similar to {@link #readClientBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyClientBook> readClientBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableClientBook> jsonClientBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableClientBook.class);
        if (!jsonClientBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonClientBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveClientBook(ReadOnlyClientBook clientBook) throws IOException {
        saveClientBook(clientBook, filePath);
    }

    /**
     * Similar to {@link #saveClientBook(ReadOnlyClientBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveClientBook(ReadOnlyClientBook clientBook, Path filePath) throws IOException {
        requireNonNull(clientBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableClientBook(clientBook), filePath);
    }

}
