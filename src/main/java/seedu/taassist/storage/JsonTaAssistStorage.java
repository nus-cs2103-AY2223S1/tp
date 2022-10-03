package seedu.taassist.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.taassist.commons.core.LogsCenter;
import seedu.taassist.commons.exceptions.DataConversionException;
import seedu.taassist.commons.exceptions.IllegalValueException;
import seedu.taassist.commons.util.FileUtil;
import seedu.taassist.commons.util.JsonUtil;
import seedu.taassist.model.ReadOnlyTaAssist;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonTaAssistStorage implements TaAssistStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaAssistStorage.class);

    private Path filePath;

    public JsonTaAssistStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaAssist> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTaAssist> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaAssist> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaAssist.class);
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
    public void saveAddressBook(ReadOnlyTaAssist addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyTaAssist)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyTaAssist addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaAssist(addressBook), filePath);
    }

}
