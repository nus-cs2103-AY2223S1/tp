package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.PersonBook;
import seedu.address.model.ReadOnlyPersonBook;

/**
 * Represents a storage for {@link PersonBook}.
 */
public interface PersonBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPersonBookFilePath();

    /**
     * Returns PersonBook data as a {@link ReadOnlyPersonBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPersonBook> readPersonBook() throws DataConversionException, IOException;

    /**
     * @see #getPersonBookFilePath()
     */
    Optional<ReadOnlyPersonBook> readPersonBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPersonBook} to the storage.
     * @param personBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePersonBook(ReadOnlyPersonBook personBook) throws IOException;

    /**
     * @see #savePersonBook(ReadOnlyPersonBook)
     */
    void savePersonBook(ReadOnlyPersonBook personBook, Path filePath) throws IOException;

}
