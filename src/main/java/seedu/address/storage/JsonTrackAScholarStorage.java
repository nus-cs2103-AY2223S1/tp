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
import seedu.address.model.ReadOnlyTrackAScholar;

/**
 * A class to access TrackAScholar data stored as a json file on the hard disk.
 */
public class JsonTrackAScholarStorage implements TrackAScholarStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTrackAScholarStorage.class);

    private Path filePath;

    public JsonTrackAScholarStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTrackAScholarFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTrackAScholar> readTrackAScholar() throws DataConversionException {
        return readTrackAScholar(filePath);
    }

    /**
     * Similar to {@link #readTrackAScholar()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTrackAScholar> readTrackAScholar(Path filePath) throws DataConversionException {
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
    public void saveTrackAScholar(ReadOnlyTrackAScholar trackAScholar) throws IOException {
        saveTrackAScholar(trackAScholar, filePath);
    }

    /**
     * Similar to {@link #saveTrackAScholar(ReadOnlyTrackAScholar)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTrackAScholar(ReadOnlyTrackAScholar trackAScholar, Path filePath) throws IOException {
        requireNonNull(trackAScholar);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(trackAScholar), filePath);
    }

}
