package seedu.modquik.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.modquik.commons.core.LogsCenter;
import seedu.modquik.commons.exceptions.DataConversionException;
import seedu.modquik.commons.exceptions.IllegalValueException;
import seedu.modquik.commons.util.FileUtil;
import seedu.modquik.commons.util.JsonUtil;
import seedu.modquik.model.ReadOnlyModQuik;

/**
 * A class to access ModQuik data stored as a json file on the hard disk.
 */
public class JsonModQuikStorage implements ModQuikStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonModQuikStorage.class);

    private Path filePath;

    public JsonModQuikStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getModQuikFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyModQuik> readModQuik() throws DataConversionException {
        return readModQuik(filePath);
    }

    /**
     * Similar to {@link #readModQuik()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyModQuik> readModQuik(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableModQuik> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableModQuik.class);
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
    public void saveModQuik(ReadOnlyModQuik modQuik) throws IOException {
        saveModQuik(modQuik, filePath);
    }

    /**
     * Similar to {@link #saveModQuik(ReadOnlyModQuik)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveModQuik(ReadOnlyModQuik modQuik, Path filePath) throws IOException {
        requireNonNull(modQuik);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableModQuik(modQuik), filePath);
    }

}
