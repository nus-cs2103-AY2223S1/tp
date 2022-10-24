package seedu.rc4hdb.storage.residentbook;

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
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ResidentBook;

/**
 * A class to access ResidentBook data stored as a json file on the hard disk.
 */
public class JsonResidentBookStorage implements ResidentBookStorage {

    public static final String RESIDENT_DATA_PATH = "resident_data.json";

    private static final Logger logger = LogsCenter.getLogger(JsonResidentBookStorage.class);

    /**
     * Convenience method to generate the resident book file path.
     */
    private Path getResidentBookFilePath(Path filePath) {
        return filePath.resolve(RESIDENT_DATA_PATH);
    }

    /**
     * Reads the resident book file data and returns a {@code ReadOnlyResidentBook}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyResidentBook> readResidentBook(Path folderPath) throws DataConversionException {
        requireNonNull(folderPath);
        Path filePath = getResidentBookFilePath(folderPath);
        logger.info(String.format("Attempting to read resident file data from: %s", filePath));

        Optional<JsonSerializableResidentBook> jsonResidentBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableResidentBook.class);
        if (!jsonResidentBook.isPresent()) {
            logger.info(String.format("No resident file found at %s", filePath));
            return Optional.empty();
        }

        try {
            return Optional.of(jsonResidentBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    /**
     * Saves the data in {@code residentBook} to the resident_data.json file in the directory with path
     * {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     */
    public void saveResidentBook(ReadOnlyResidentBook residentBook, Path folderPath) throws IOException {
        requireAllNonNull(residentBook, folderPath);
        Path filePath = getResidentBookFilePath(folderPath);
        logger.info(String.format("Attempting to save resident file data to: %s", filePath));
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableResidentBook(residentBook), filePath);
    }

    /**
     * Deletes the resident book data file that is in the directory with path {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     */
    public void deleteResidentBookFile(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        Path filePath = getResidentBookFilePath(folderPath);
        logger.info(String.format("Attempting to delete resident file data at: %s", filePath));
        FileUtil.deleteFile(filePath);
    }

    /**
     * Creates an empty resident book data file that is in the directory with path {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     */
    public void createResidentBookFile(Path folderPath) throws IOException {
        requireNonNull(folderPath);
        Path filePath = getResidentBookFilePath(folderPath);
        logger.info(String.format("Attempting to save resident file data to: %s", filePath));
        FileUtil.createFile(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableResidentBook(new ResidentBook()), filePath);
    }

}
