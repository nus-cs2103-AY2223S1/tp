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
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonArchivedTaskBookStorage implements ArchivedTaskBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonArchivedTaskBookStorage.class);

    private final Path filePath;

    public JsonArchivedTaskBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getArchivedTaskBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readArchivedTaskBook() throws DataConversionException, IOException {
        return readArchivedTaskBook(filePath);
    }

    /**
     * Similar to {@link #readArchivedTaskBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyAddressBook> readArchivedTaskBook(Path filePath)
            throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableArchivedTaskBook> jsonTaskBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableArchivedTaskBook.class);

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
    public void saveArchivedTaskBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveArchivedTaskBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveArchivedTaskBook(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveArchivedTaskBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableArchivedTaskBook(addressBook), filePath);
    }


}
