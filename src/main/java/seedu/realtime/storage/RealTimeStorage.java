package seedu.realtime.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.realtime.commons.exceptions.DataConversionException;
import seedu.realtime.model.ReadOnlyRealTime;
import seedu.realtime.model.RealTime;

/**
 * Represents a storage for {@link RealTime}.
 */
public interface RealTimeStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getRealTimeFilePath();

    /**
     * Returns realTime data as a {@link ReadOnlyRealTime}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyRealTime> readRealTime() throws DataConversionException, IOException;

    /**
     * @see #getRealTimeFilePath()
     */
    Optional<ReadOnlyRealTime> readRealTime(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyRealTime} to the storage.
     * @param realTime cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRealTime(ReadOnlyRealTime realTime) throws IOException;

    /**
     * @see #saveRealTime(ReadOnlyRealTime)
     */
    void saveRealTime(ReadOnlyRealTime realTime, Path filePath) throws IOException;

}
