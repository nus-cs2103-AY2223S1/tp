package friday.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import friday.commons.exceptions.DataConversionException;
import friday.model.Friday;
import friday.model.ReadOnlyFriday;

/**
 * Represents a storage for {@link Friday}.
 */
public interface FridayStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFridayFilePath();

    /**
     * Returns Friday data as a {@link ReadOnlyFriday}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFriday> readFriday() throws DataConversionException, IOException;

    /**
     * @see #getFridayFilePath()
     */
    Optional<ReadOnlyFriday> readFriday(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFriday} to the storage.
     * @param friday cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFriday(ReadOnlyFriday friday) throws IOException;

    /**
     * @see #saveFriday(ReadOnlyFriday)
     */
    void saveFriday(ReadOnlyFriday friday, Path filePath) throws IOException;

}
