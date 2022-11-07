package seedu.application.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.application.commons.core.LogsCenter;
import seedu.application.commons.exceptions.DataConversionException;
import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.commons.util.FileUtil;
import seedu.application.commons.util.JsonUtil;
import seedu.application.model.ReadOnlyApplicationBook;

/**
 * A class to access ApplicationBook data stored as a json file on the hard disk.
 */
public class JsonApplicationBookStorage implements ApplicationBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonApplicationBookStorage.class);

    private Path filePath;

    public JsonApplicationBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getApplicationBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyApplicationBook> readApplicationBook() throws DataConversionException {
        return readApplicationBook(filePath);
    }

    /**
     * Similar to {@link #readApplicationBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyApplicationBook> readApplicationBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableApplicationBook> jsonApplicationBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableApplicationBook.class);
        if (!jsonApplicationBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonApplicationBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveApplicationBook(ReadOnlyApplicationBook applicationBook) throws IOException {
        saveApplicationBook(applicationBook, filePath);
    }

    /**
     * Similar to {@link #saveApplicationBook(ReadOnlyApplicationBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveApplicationBook(ReadOnlyApplicationBook applicationBook, Path filePath) throws IOException {
        requireNonNull(applicationBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableApplicationBook(applicationBook), filePath);
    }

}
