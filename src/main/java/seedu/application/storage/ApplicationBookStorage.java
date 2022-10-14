package seedu.application.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.application.commons.exceptions.DataConversionException;
import seedu.application.model.ReadOnlyApplicationBook;

/**
 * Represents a storage for {@link seedu.application.model.ApplicationBook}.
 */
public interface ApplicationBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getApplicationBookFilePath();

    /**
     * Returns Application data as a {@link ReadOnlyApplicationBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyApplicationBook> readApplicationBook() throws DataConversionException, IOException;

    /**
     * @see #getApplicationBookFilePath()
     */
    Optional<ReadOnlyApplicationBook> readApplicationBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyApplicationBook} to the storage.
     * @param applicationBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveApplicationBook(ReadOnlyApplicationBook applicationBook) throws IOException;

    /**
     * @see #saveApplicationBook(ReadOnlyApplicationBook)
     */
    void saveApplicationBook(ReadOnlyApplicationBook applicationBook, Path filePath) throws IOException;

}
