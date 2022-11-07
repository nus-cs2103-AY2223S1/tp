package seedu.condonery.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.condonery.commons.exceptions.DataConversionException;
import seedu.condonery.model.client.ReadOnlyClientDirectory;

/**
 * Represents a storage for {@link seedu.condonery.model.ClientDirectory}.
 */
public interface ClientDirectoryStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getClientDirectoryFilePath();

    /**
     * Returns ClientDirectory data as a {@link ReadOnlyClientDirectory}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyClientDirectory> readClientDirectory() throws DataConversionException, IOException;

    /**
     * @see #getClientDirectoryFilePath()
     */
    Optional<ReadOnlyClientDirectory> readClientDirectory(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyClientDirectory} to the storage.
     * @param clientDirectory cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveClientDirectory(ReadOnlyClientDirectory clientDirectory) throws IOException;

    /**
     * @see #saveClientDirectory(ReadOnlyClientDirectory)
     */
    void saveClientDirectory(ReadOnlyClientDirectory clientDirectory, Path filePath) throws IOException;

}
