package seedu.trackascholar.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.trackascholar.commons.exceptions.DataConversionException;
import seedu.trackascholar.model.ReadOnlyTrackAScholar;
import seedu.trackascholar.model.TrackAScholar;

/**
 * Represents a storage for {@link TrackAScholar}.
 */
public interface TrackAScholarStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTrackAScholarFilePath();

    /**
     * Returns TrackAScholar data as a {@link ReadOnlyTrackAScholar}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTrackAScholar> readTrackAScholar() throws DataConversionException, IOException;

    /**
     * @see #getTrackAScholarFilePath()
     */
    Optional<ReadOnlyTrackAScholar> readTrackAScholar(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTrackAScholar} to the storage.
     *
     * @param trackAScholar cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTrackAScholar(ReadOnlyTrackAScholar trackAScholar) throws IOException;

    /**
     * @see #saveTrackAScholar(ReadOnlyTrackAScholar)
     */
    void saveTrackAScholar(ReadOnlyTrackAScholar trackAScholar, Path filePath) throws IOException;

}
