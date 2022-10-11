package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTaskBook;

/**
 * Represents a storage for {@link seedu.address.model.TaskBook}
 */
public interface TaskBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTaskBookFilePath();

    /**
     * Returns TaskBook data as a {@link ReadOnlyTaskBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTaskBook> readTaskBook() throws DataConversionException, IOException;

    /**
     * @see #getTaskBookFilePath()
     */
    Optional<ReadOnlyTaskBook> readTaskBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTaskBook} to the storage.
     * @param taskBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTaskBook(ReadOnlyTaskBook taskBook) throws IOException;

    /**
     * @see #saveTaskBook(ReadOnlyTaskBook, Path)
     */
    void saveTaskBook(ReadOnlyTaskBook taskBook, Path filePath) throws IOException;
}
