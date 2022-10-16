package seedu.uninurse.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.uninurse.commons.core.LogsCenter;
import seedu.uninurse.commons.exceptions.DataConversionException;
import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.commons.util.FileUtil;
import seedu.uninurse.commons.util.JsonUtil;
import seedu.uninurse.model.ReadOnlyUninurseBook;

/**
 * A class to access UninurseBook data stored as a json file on the hard disk.
 */
public class JsonUninurseBookStorage implements UninurseBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUninurseBookStorage.class);

    private Path filePath;

    public JsonUninurseBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getUninurseBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyUninurseBook> readUninurseBook() throws DataConversionException {
        return readUninurseBook(filePath);
    }

    /**
     * Similar to {@link #readUninurseBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyUninurseBook> readUninurseBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableUninurseBook> jsonUninurseBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableUninurseBook.class);
        if (!jsonUninurseBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUninurseBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveUninurseBook(ReadOnlyUninurseBook uninurseBook) throws IOException {
        saveUninurseBook(uninurseBook, filePath);
    }

    /**
     * Similar to {@link #saveUninurseBook(ReadOnlyUninurseBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveUninurseBook(ReadOnlyUninurseBook uninurseBook, Path filePath) throws IOException {
        requireNonNull(uninurseBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUninurseBook(uninurseBook), filePath);
    }

}
