package seedu.uninurse.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.uninurse.commons.exceptions.DataConversionException;
import seedu.uninurse.model.ReadOnlyUninurseBook;

/**
 * Represents a storage for {@link seedu.uninurse.model.UninurseBook}.
 */
public interface UninurseBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getUninurseBookFilePath();

    /**
     * Returns UninurseBook data as a {@link ReadOnlyUninurseBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyUninurseBook> readUninurseBook() throws DataConversionException, IOException;

    /**
     * @see #getUninurseBookFilePath()
     */
    Optional<ReadOnlyUninurseBook> readUninurseBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyUninurseBook} to the storage.
     * @param uninurseBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUninurseBook(ReadOnlyUninurseBook uninurseBook) throws IOException;

    /**
     * @see #saveUninurseBook(ReadOnlyUninurseBook)
     */
    void saveUninurseBook(ReadOnlyUninurseBook uninurseBook, Path filePath) throws IOException;

}
