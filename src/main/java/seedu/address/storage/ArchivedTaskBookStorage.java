package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface ArchivedTaskBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getArchivedTaskBookFilePath();

    /**
     * Returns TaskBook data as a {@link ReadOnlyAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAddressBook> readArchivedTaskBook() throws DataConversionException, IOException;

    /**
     * @see #getArchivedTaskBookFilePath()
     */
    Optional<ReadOnlyAddressBook> readArchivedTaskBook(Path filePath) throws DataConversionException, IOException;

    void saveArchivedTaskBook(ReadOnlyAddressBook addressBook) throws IOException;

    void saveArchivedTaskBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException;

}

