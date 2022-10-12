package jeryl.fyp.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import jeryl.fyp.commons.exceptions.DataConversionException;
import jeryl.fyp.model.ReadOnlyFypManager;

/**
 * Represents a storage for {@link jeryl.fyp.model.FypManager}.
 */
public interface FypManagerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFypManagerFilePath();

    /**
     * Returns FypManager data as a {@link ReadOnlyFypManager}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFypManager> readFypManager() throws DataConversionException, IOException;

    /**
     * @see #getFypManagerFilePath()
     */
    Optional<ReadOnlyFypManager> readFypManager(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFypManager} to the storage.
     * @param fypManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFypManager(ReadOnlyFypManager fypManager) throws IOException;

    /**
     * @see #saveFypManager(ReadOnlyFypManager)
     */
    void saveFypManager(ReadOnlyFypManager fypManager, Path filePath) throws IOException;

}
