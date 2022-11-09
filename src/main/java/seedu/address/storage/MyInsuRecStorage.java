package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.MyInsuRec;
import seedu.address.model.ReadOnlyMyInsuRec;

/**
 * Represents a storage for {@link MyInsuRec}.
 */
public interface MyInsuRecStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMyInsuRecFilePath();

    /**
     * Returns MyInsuRec data as a {@link ReadOnlyMyInsuRec}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMyInsuRec> readMyInsuRec() throws DataConversionException, IOException;

    /**
     * @see #getMyInsuRecFilePath()
     */
    Optional<ReadOnlyMyInsuRec> readMyInsuRec(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMyInsuRec} to the storage.
     * @param myInsuRec cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMyInsuRec(ReadOnlyMyInsuRec myInsuRec) throws IOException;

    /**
     * @see #saveMyInsuRec(ReadOnlyMyInsuRec)
     */
    void saveMyInsuRec(ReadOnlyMyInsuRec myInsuRec, Path filePath) throws IOException;

}
