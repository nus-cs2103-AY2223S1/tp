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
import seedu.address.model.ReadOnlyPropertyBook;

/**
 * A class to access BuyerBook data stored as a json file on the hard disk.
 */
public class JsonPropertyBookStorage implements PropertyBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPropertyBookStorage.class);

    private Path filePath;

    public JsonPropertyBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPropertyBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPropertyBook> readPropertyBook() throws DataConversionException {
        return readPropertyBook(filePath);
    }

    /**
     * Similar to {@link #readPropertyBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPropertyBook> readPropertyBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePropertyBook> jsonPropertyBook = JsonUtil.readJsonFile(
                filePath, JsonSerializablePropertyBook.class);
        if (!jsonPropertyBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPropertyBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePropertyBook(ReadOnlyPropertyBook propertyBook) throws IOException {
        savePropertyBook(propertyBook, filePath);
    }

    /**
     * Similar to {@link #savePropertyBook(ReadOnlyPropertyBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePropertyBook(ReadOnlyPropertyBook propertyBook, Path filePath) throws IOException {
        requireNonNull(propertyBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePropertyBook(propertyBook), filePath);
    }

}
