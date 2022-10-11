package bookface.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import bookface.commons.exceptions.DataConversionException;
import bookface.model.BookFace;
import bookface.model.ReadOnlyBookFace;

/**
 * Represents a storage for {@link BookFace}.
 */
public interface BookFaceStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getBookFaceFilePath();

    /**
     * Returns BookFace data as a {@link ReadOnlyBookFace}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBookFace> readBookFace() throws DataConversionException, IOException;

    /**
     * @see #getBookFaceFilePath()
     */
    Optional<ReadOnlyBookFace> readBookFace(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyBookFace} to the storage.
     * @param bookFace cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveBookFace(ReadOnlyBookFace bookFace) throws IOException;

    /**
     * @see #saveBookFace(ReadOnlyBookFace)
     */
    void saveBookFace(ReadOnlyBookFace bookFace, Path filePath) throws IOException;

}
