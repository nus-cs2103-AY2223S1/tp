package seedu.rc4hdb.storage;

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
     * Returns the file path of the data file.
     */
    Path getResidentBookFilePath();

    /**
     * Sets the file path to the {@code filePath}.
     */
    void setResidentBookFilePath(Path filePath);

    /**
     * Returns ResidentBook data as a {@link ReadOnlyResidentBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyResidentBook> readResidentBook() throws DataConversionException, IOException;

    /**
     * @see #getResidentBookFilePath()
     */
    Optional<ReadOnlyResidentBook> readResidentBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyResidentBook} to the storage.
     * @param residentBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveResidentBook(ReadOnlyResidentBook residentBook) throws IOException;

    /**
     * @see #saveResidentBook(ReadOnlyResidentBook)
     */
    void saveResidentBook(ReadOnlyResidentBook residentBook, Path filePath) throws IOException;

    /**
     * Deletes the resident book data file corresponding to {@code filePath}.
     *
     * @param filePath path of the data file to be deleted. Cannot be null.
     * @throws IOException if there was any problem deleting the file.
     */
    void deleteResidentBookFile(Path filePath) throws IOException;

    /**
     * Creates the resident book data file corresponding to {@code filePath}.
     *
     * @param filePath path of the data file to be created. Cannot be null.
     * @throws IOException if there was any problem creating the file.
     */
    void createResidentBookFile(Path filePath) throws IOException;

}
