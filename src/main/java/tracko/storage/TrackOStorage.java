package tracko.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import tracko.commons.exceptions.DataConversionException;
import tracko.model.ReadOnlyTrackO;

/**
 * Represents a storage for {@link TrackO}
 */
public interface TrackOStorage {
    Path getOrdersFilePath();

    /**
     * Returns TrackO data as a {@link ReadOnlyTrackO}
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTrackO> readTrackO() throws DataConversionException, IOException;

    Optional<ReadOnlyTrackO> readTrackO(Path ordersFilePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTrackO} to the storage.
     * @param trackO cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTrackO(ReadOnlyTrackO trackO) throws IOException;

    void saveTrackO(ReadOnlyTrackO trackO, Path ordersFilePath) throws IOException;
}
