package seedu.intrack.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.intrack.commons.exceptions.DataConversionException;
import seedu.intrack.model.InTrack;
import seedu.intrack.model.ReadOnlyInTrack;

/**
 * Represents a storage for {@link InTrack}.
 */
public interface InTrackStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInTrackFilePath();

    /**
     * Returns InTrack data as a {@link ReadOnlyInTrack}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyInTrack> readInTrack() throws DataConversionException, IOException;

    /**
     * @see #getInTrackFilePath()
     */
    Optional<ReadOnlyInTrack> readInTrack(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyInTrack} to the storage.
     * @param inTrack cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInTrack(ReadOnlyInTrack inTrack) throws IOException;

    /**
     * @see #saveInTrack(ReadOnlyInTrack)
     */
    void saveInTrack(ReadOnlyInTrack inTrack, Path filePath) throws IOException;

}
