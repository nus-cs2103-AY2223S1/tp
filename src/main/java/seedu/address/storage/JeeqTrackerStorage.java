package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyJeeqTracker;

/**
 * Represents a storage for {@link seedu.address.model.JeeqTracker}.
 */
public interface JeeqTrackerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getJeeqTrackerFilePath();

    /**
     * Returns JeeqTracker data as a {@link ReadOnlyJeeqTracker}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyJeeqTracker> readJeeqTracker() throws DataConversionException, IOException;

    /**
     * @see #getJeeqTrackerFilePath()
     */
    Optional<ReadOnlyJeeqTracker> readJeeqTracker(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyJeeqTracker} to the storage.
     * @param jeeqTracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveJeeqTracker(ReadOnlyJeeqTracker jeeqTracker) throws IOException;

    /**
     * @see #saveJeeqTracker(ReadOnlyJeeqTracker)
     */
    void saveJeeqTracker(ReadOnlyJeeqTracker jeeqTracker, Path filePath) throws IOException;

}
