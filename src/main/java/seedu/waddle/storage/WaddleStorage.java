package seedu.waddle.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.waddle.commons.exceptions.DataConversionException;
import seedu.waddle.model.ReadOnlyWaddle;
import seedu.waddle.model.Waddle;

/**
 * Represents a storage for {@link Waddle}.
 */
public interface WaddleStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getWaddleFilePath();

    /**
     * Returns Waddle data as a {@link ReadOnlyWaddle}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyWaddle> readWaddle() throws DataConversionException, IOException;

    /**
     * @see #getWaddleFilePath()
     */
    Optional<ReadOnlyWaddle> readWaddle(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyWaddle} to the storage.
     * @param waddle cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveWaddle(ReadOnlyWaddle waddle) throws IOException;

    /**
     * @see #saveWaddle(ReadOnlyWaddle)
     */
    void saveWaddle(ReadOnlyWaddle waddle, Path filePath) throws IOException;

}
