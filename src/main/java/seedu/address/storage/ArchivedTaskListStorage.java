package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ArchivedTaskList;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Represents a storage for {@link ArchivedTaskList}.
 */
public interface ArchivedTaskListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getArchivedTaskListFilePath();

    /**
     * Returns TaskBook data as a {@link ReadOnlyAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAddressBook> readArchivedTaskList() throws DataConversionException, IOException;

    /**
     * @see #getArchivedTaskListFilePath()
     */
    Optional<ReadOnlyAddressBook> readArchivedTaskList(Path filePath) throws DataConversionException, IOException;

    void saveArchivedTaskList(ReadOnlyAddressBook addressBook) throws IOException;

    void saveArchivedTaskList(ReadOnlyAddressBook addressBook, Path filePath) throws IOException;

}

