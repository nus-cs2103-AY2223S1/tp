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
import seedu.address.model.ReadOnlyDatabase;

/**
 * A class to access Database data stored as a json file on the hard disk.
 */
public class JsonDatabaseStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDatabaseStorage.class);

    private Path filePath;

    public JsonDatabaseStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDatabase> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyDatabase> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableDatabase> jsonAddressBook = JsonUtil.readJsonFile(
            filePath, JsonSerializableDatabase.class);
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
    public void saveAddressBook(ReadOnlyDatabase addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyDatabase)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyDatabase addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDatabase(addressBook), filePath);
    }

}
