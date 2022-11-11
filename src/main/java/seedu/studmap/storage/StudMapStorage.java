package seedu.studmap.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.studmap.commons.exceptions.DataConversionException;
import seedu.studmap.model.ReadOnlyStudMap;
import seedu.studmap.model.StudMap;

/**
 * Represents a storage for {@link StudMap}.
 */
public interface StudMapStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getStudMapFilePath();

    /**
     * Returns StudMap data as a {@link ReadOnlyStudMap}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyStudMap> readStudMap() throws DataConversionException, IOException;

    /**
     * @see #getStudMapFilePath()
     */
    Optional<ReadOnlyStudMap> readStudMap(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyStudMap} to the storage.
     * @param studMap cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveStudMap(ReadOnlyStudMap studMap) throws IOException;

    /**
     * @see #saveStudMap(ReadOnlyStudMap)
     */
    void saveStudMap(ReadOnlyStudMap studMap, Path filePath) throws IOException;

}
