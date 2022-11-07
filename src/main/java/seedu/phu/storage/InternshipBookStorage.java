package seedu.phu.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.phu.commons.exceptions.DataConversionException;
import seedu.phu.model.InternshipBook;
import seedu.phu.model.ReadOnlyInternshipBook;

/**
 * Represents a storage for {@link InternshipBook}.
 */
public interface InternshipBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInternshipBookFilePath();

    /**
     * Returns InternshipBook data as a {@link ReadOnlyInternshipBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyInternshipBook> readInternshipBook() throws DataConversionException, IOException;

    /**
     * @see #getInternshipBookFilePath()
     */
    Optional<ReadOnlyInternshipBook> readInternshipBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyInternshipBook} to the storage.
     * @param internshipBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInternshipBook(ReadOnlyInternshipBook internshipBook) throws IOException;

    /**
     * @see #saveInternshipBook(ReadOnlyInternshipBook)
     */
    void saveInternshipBook(ReadOnlyInternshipBook internshipBook, Path filePath) throws IOException;

}
