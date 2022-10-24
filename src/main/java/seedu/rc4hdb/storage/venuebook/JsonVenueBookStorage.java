package seedu.rc4hdb.storage.venuebook;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.rc4hdb.commons.core.LogsCenter;
import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.commons.exceptions.IllegalValueException;
import seedu.rc4hdb.commons.util.FileUtil;
import seedu.rc4hdb.commons.util.JsonUtil;
import seedu.rc4hdb.model.ReadOnlyVenueBook;
import seedu.rc4hdb.model.VenueBook;

/**
 * A class to access VenueBook data stored as a json file on the hard disk.
 */
public class JsonVenueBookStorage implements VenueBookStorage {

    public static final String VENUE_DATA_PATH = "venue_data.json";

    private static final Logger logger = LogsCenter.getLogger(JsonVenueBookStorage.class);

    /**
     * Convenience method to generate the venue book file path.
     */
    private Path getVenueBookFilePath(Path filePath) {
        return filePath.resolve(VENUE_DATA_PATH);
    }

    /**
     * Reads the venue book file data and returns a {@code ReadOnlyVenueBook}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyVenueBook> readVenueBook(Path folderPath) throws DataConversionException {
        requireNonNull(folderPath);
        Path filePath = getVenueBookFilePath(folderPath);
        logger.info(String.format("Attempting to read venue file data from: %s", filePath));

        Optional<JsonSerializableVenueBook> jsonVenueBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableVenueBook.class);
        if (!jsonVenueBook.isPresent()) {
            logger.warning(String.format("No venue file found at %s", filePath));
            return Optional.empty();
        }

        try {
            return Optional.of(jsonVenueBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    /**
     * Saves the data in {@code venueBook} to the venue_data.json file in the directory with path
     * {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     */
    public void saveVenueBook(ReadOnlyVenueBook venueBook, Path folderPath) throws IOException {
        requireAllNonNull(venueBook, folderPath);
        Path filePath = getVenueBookFilePath(folderPath);
        logger.info(String.format("Attempting to save venue file data to: %s", filePath));
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableVenueBook(venueBook), filePath);
    }

    /**
     * Deletes the venue book data file that is in the directory with path {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     */
    public void deleteVenueBookFile(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        Path filePath = getVenueBookFilePath(folderPath);
        logger.info(String.format("Attempting to delete venue file data at: %s", filePath));
        FileUtil.deleteFile(filePath);
    }

    /**
     * Creates an empty venue book data file that is in the directory with path {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     */
    public void createVenueBookFile(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        Path filePath = getVenueBookFilePath(folderPath);
        logger.info(String.format("Attempting to save venue file data to: %s", filePath));
        FileUtil.createFile(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableVenueBook(new VenueBook()), filePath);
    }

}
