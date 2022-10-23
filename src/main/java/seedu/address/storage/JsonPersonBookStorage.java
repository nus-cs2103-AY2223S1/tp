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
import seedu.address.model.ReadOnlyPersonBook;

/**
 * A class to access PersonBook data stored as a json file on the hard disk.
 */
public class JsonPersonBookStorage implements PersonBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPersonBookStorage.class);

    private Path filePath;

    public JsonPersonBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPersonBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPersonBook> readPersonBook() throws DataConversionException {
        return readPersonBook(filePath);
    }

    /**
     * Similar to {@link #readPersonBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPersonBook> readPersonBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePersonBook(ReadOnlyPersonBook addressBook) throws IOException {
        savePersonBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #savePersonBook(ReadOnlyPersonBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePersonBook(ReadOnlyPersonBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);
    }

}
