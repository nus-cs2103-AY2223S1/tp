package seedu.workbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.workbook.commons.exceptions.DataConversionException;
import seedu.workbook.model.ReadOnlyWorkBook;

/**
 * Represents a storage for {@link seedu.workbook.model.WorkBook}.
 */
public interface WorkBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getWorkBookFilePath();

    /**
     * Returns WorkBook data as a {@link ReadOnlyWorkBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyWorkBook> readWorkBook() throws DataConversionException, IOException;

    /**
     * @see #getWorkBookFilePath()
     */
    Optional<ReadOnlyWorkBook> readWorkBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyWorkBook} to the storage.
     * @param workBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveWorkBook(ReadOnlyWorkBook workBook) throws IOException;

    /**
     * @see #saveWorkBook(ReadOnlyWorkBook)
     */
    void saveWorkBook(ReadOnlyWorkBook workBook, Path filePath) throws IOException;

}
