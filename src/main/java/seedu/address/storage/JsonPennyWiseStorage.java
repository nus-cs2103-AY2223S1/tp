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
import seedu.address.model.ReadOnlyPennyWise;

/**
 * A class to access PennyWise data stored as a json file on the hard disk.
 */
public class JsonPennyWiseStorage implements PennyWiseStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPennyWiseStorage.class);

    private Path filePath;

    public JsonPennyWiseStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPennyWiseFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPennyWise> readPennyWise() throws DataConversionException {
        return readPennyWise(filePath);
    }

    /**
     * Similar to {@link #readPennyWise()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPennyWise> readPennyWise(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePennyWise> jsonAddressBook = JsonUtil.readJsonFile(
            filePath, JsonSerializablePennyWise.class);
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
    public void savePennyWise(ReadOnlyPennyWise addressBook) throws IOException {
        savePennyWise(addressBook, filePath);
    }

    /**
     * Similar to {@link #savePennyWise(ReadOnlyPennyWise)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePennyWise(ReadOnlyPennyWise pennyWise, Path filePath) throws IOException {
        requireNonNull(pennyWise);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePennyWise(pennyWise), filePath);
    }

}
