package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ClientBook;
import seedu.address.model.ReadOnlyClientBook;

/**
 * Represents a storage for {@link ClientBook}.
 */
public interface ClientBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getClientBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyClientBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyClientBook> readClientBook() throws DataConversionException, IOException;

    /**
     * @see #getClientBookFilePath()
     */
    Optional<ReadOnlyClientBook> readClientBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyClientBook} to the storage.
     * @param clientBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveClientBook(ReadOnlyClientBook clientBook) throws IOException;

    /**
     * @see #saveClientBook(ReadOnlyClientBook)
     */
    void saveClientBook(ReadOnlyClientBook clientBook, Path filePath) throws IOException;

}
