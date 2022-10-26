package seedu.rc4hdb.storage.residentbook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.model.ReadOnlyResidentBook;

/**
 * Represents a storage for {@link seedu.rc4hdb.model.ResidentBook}.
 */
public interface ResidentBookStorage {

    /**
     * Reads the resident book file data and returns a {@code ReadOnlyResidentBook}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    Optional<ReadOnlyResidentBook> readResidentBook(Path folderPath) throws DataConversionException, IOException;

    /**
     * Saves the data in {@code residentBook} to the resident_data.json file in the directory with path
     * {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     */
    void saveResidentBook(ReadOnlyResidentBook residentBook, Path folderPath) throws IOException;

    /**
     * Deletes the resident book data file that is in the directory with path {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     * @throws IOException if there was any problem deleting the file.
     */
    void deleteResidentBookFile(Path folderPath) throws IOException;

    /**
     * Creates an empty resident book data file that is in the directory with path {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     * @throws IOException if there was any problem creating the file.
     */
    void createResidentBookFile(Path folderPath) throws IOException;

}
