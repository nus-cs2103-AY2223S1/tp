package hobbylist.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import hobbylist.commons.core.LogsCenter;
import hobbylist.commons.exceptions.DataConversionException;
import hobbylist.commons.exceptions.IllegalValueException;
import hobbylist.commons.util.FileUtil;
import hobbylist.commons.util.JsonUtil;
import hobbylist.model.ReadOnlyHobbyList;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonHobbyListStorage implements HobbyListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonHobbyListStorage.class);

    private Path filePath;

    public JsonHobbyListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyHobbyList> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyHobbyList> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableHobbyList> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableHobbyList.class);
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
    public void saveAddressBook(ReadOnlyHobbyList addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyHobbyList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyHobbyList addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableHobbyList(addressBook), filePath);
    }

}
