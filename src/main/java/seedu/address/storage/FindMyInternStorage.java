package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FindMyIntern;
import seedu.address.model.ReadOnlyFindMyIntern;

/**
 * Represents a storage for {@link FindMyIntern}.
 */
public interface FindMyInternStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFindMyInternFilePath();

    /**
     * Returns FindMyIntern data as a {@link ReadOnlyFindMyIntern}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFindMyIntern> readFindMyIntern() throws DataConversionException, IOException;

    /**
     * @see #getFindMyInternFilePath()
     */
    Optional<ReadOnlyFindMyIntern> readFindMyIntern(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFindMyIntern} to the storage.
     * @param findMyIntern cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFindMyIntern(ReadOnlyFindMyIntern findMyIntern) throws IOException;

    /**
     * @see #saveFindMyIntern(ReadOnlyFindMyIntern)
     */
    void saveFindMyIntern(ReadOnlyFindMyIntern findMyIntern, Path filePath) throws IOException;

}
