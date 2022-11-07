package seedu.condonery.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.condonery.commons.exceptions.DataConversionException;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;

/**
 * Represents a storage for {@link seedu.condonery.model.PropertyDirectory}.
 */
public interface PropertyDirectoryStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPropertyDirectoryFilePath();

    /**
     * Returns PropertyDirectory data as a {@link ReadOnlyPropertyDirectory}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPropertyDirectory> readPropertyDirectory() throws DataConversionException, IOException;

    /**
     * @see #getPropertyDirectoryFilePath()
     */
    Optional<ReadOnlyPropertyDirectory> readPropertyDirectory(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPropertyDirectory} to the storage.
     * @param propertyDirectory cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePropertyDirectory(ReadOnlyPropertyDirectory propertyDirectory) throws IOException;

    /**
     * @see #savePropertyDirectory(ReadOnlyPropertyDirectory)
     */
    void savePropertyDirectory(ReadOnlyPropertyDirectory propertyDirectory, Path filePath) throws IOException;

}
