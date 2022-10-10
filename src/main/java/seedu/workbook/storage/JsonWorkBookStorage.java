package seedu.workbook.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.workbook.commons.core.LogsCenter;
import seedu.workbook.commons.exceptions.DataConversionException;
import seedu.workbook.commons.exceptions.IllegalValueException;
import seedu.workbook.commons.util.FileUtil;
import seedu.workbook.commons.util.JsonUtil;
import seedu.workbook.model.ReadOnlyWorkBook;

/**
 * A class to access WorkBook data stored as a json file on the hard disk.
 */
public class JsonWorkBookStorage implements WorkBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonWorkBookStorage.class);

    private Path filePath;

    public JsonWorkBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getWorkBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyWorkBook> readWorkBook() throws DataConversionException {
        return readWorkBook(filePath);
    }

    /**
     * Similar to {@link #readWorkBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyWorkBook> readWorkBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableWorkBook> jsonWorkBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableWorkBook.class);
        if (!jsonWorkBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonWorkBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveWorkBook(ReadOnlyWorkBook workBook) throws IOException {
        saveWorkBook(workBook, filePath);
    }

    /**
     * Similar to {@link #saveWorkBook(ReadOnlyWorkBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveWorkBook(ReadOnlyWorkBook workBook, Path filePath) throws IOException {
        requireNonNull(workBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableWorkBook(workBook), filePath);
    }

}
