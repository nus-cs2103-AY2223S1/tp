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
import seedu.address.model.ReadOnlyBuyerBook;

/**
 * A class to access BuyerBook data stored as a json file on the hard disk.
 */
public class JsonBuyerBookStorage implements BuyerBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonBuyerBookStorage.class);

    private Path filePath;

    public JsonBuyerBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getBuyerBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBuyerBook> readBuyerBook() throws DataConversionException {
        return readBuyerBook(filePath);
    }

    /**
     * Similar to {@link #readBuyerBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyBuyerBook> readBuyerBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableBuyerBook> jsonBuyerBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableBuyerBook.class);
        if (!jsonBuyerBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonBuyerBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveBuyerBook(ReadOnlyBuyerBook buyerBook) throws IOException {
        saveBuyerBook(buyerBook, filePath);
    }

    /**
     * Similar to {@link #saveBuyerBook(ReadOnlyBuyerBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveBuyerBook(ReadOnlyBuyerBook buyerBook, Path filePath) throws IOException {
        requireNonNull(buyerBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBuyerBook(buyerBook), filePath);
    }

}
