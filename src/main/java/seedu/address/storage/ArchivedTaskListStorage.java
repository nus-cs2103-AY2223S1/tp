package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ArchivedTaskList;
import seedu.address.model.ReadOnlyTaskList;

/**
 * Represents a storage for {@link ArchivedTaskList}.
 */
public interface ArchivedTaskListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getArchivedTaskListFilePath();

    /**
     * Returns TaskBook data as a {@link ReadOnlyTaskList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTaskList> readArchivedTaskList() throws DataConversionException, IOException;

    /**
     * @see #getArchivedTaskListFilePath()
     */
    Optional<ReadOnlyTaskList> readArchivedTaskList(Path filePath) throws DataConversionException, IOException;

    void saveArchivedTaskList(ReadOnlyTaskList addressBook) throws IOException;

    void saveArchivedTaskList(ReadOnlyTaskList addressBook, Path filePath) throws IOException;

}

