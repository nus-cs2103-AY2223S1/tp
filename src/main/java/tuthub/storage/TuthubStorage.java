package tuthub.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import tuthub.commons.exceptions.DataConversionException;
import tuthub.model.ReadOnlyTuthub;

/**
 * Represents a storage for {@link tuthub.model.Tuthub}.
 */
public interface TuthubStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTuthubFilePath();

    /**
     * Returns Tuthub data as a {@link ReadOnlyTuthub}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTuthub> readTuthub() throws DataConversionException, IOException;

    /**
     * @see #getTuthubFilePath()
     */
    Optional<ReadOnlyTuthub> readTuthub(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTuthub} to the storage.
     * @param tuthub cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTuthub(ReadOnlyTuthub tuthub) throws IOException;

    /**
     * @see #saveTuthub(ReadOnlyTuthub)
     */
    void saveTuthub(ReadOnlyTuthub tuthub, Path filePath) throws IOException;

}
