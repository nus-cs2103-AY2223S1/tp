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
import seedu.address.model.ReadOnlyHealthContact;

/**
 * A class to access HealthContact data stored as a json file on the hard disk.
 */
public class JsonHealthContactStorage implements HealthContactStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonHealthContactStorage.class);

    private Path filePath;

    public JsonHealthContactStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getHealthContactFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyHealthContact> readHealthContact() throws DataConversionException {
        return readHealthContact(filePath);
    }

    /**
     * Similar to {@link #readHealthContact()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyHealthContact> readHealthContact(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableHealthContact> jsonHealthContact = JsonUtil.readJsonFile(
                filePath, JsonSerializableHealthContact.class);
        if (!jsonHealthContact.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonHealthContact.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveHealthContact(ReadOnlyHealthContact healthContact) throws IOException {
        saveHealthContact(healthContact, filePath);
    }

    /**
     * Similar to {@link #saveHealthContact(ReadOnlyHealthContact)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveHealthContact(ReadOnlyHealthContact healthContact, Path filePath) throws IOException {
        requireNonNull(healthContact);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableHealthContact(healthContact), filePath);
    }

}
