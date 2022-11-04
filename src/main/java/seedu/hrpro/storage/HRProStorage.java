package seedu.hrpro.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.hrpro.commons.exceptions.DataConversionException;
import seedu.hrpro.model.ReadOnlyHRPro;

/**
 * Represents a storage for {@link seedu.hrpro.model.HRPro}.
 */
public interface HRProStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getHRProFilePath();

    /**
     * Returns HRPro data as a {@link ReadOnlyHRPro}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyHRPro> readHRPro() throws DataConversionException, IOException;

    /**
     * @see #getHRProFilePath()
     */
    Optional<ReadOnlyHRPro> readHRPro(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyHRPro} to the storage.
     * @param hrPro cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveHRPro(ReadOnlyHRPro hrPro) throws IOException;

    /**
     * @see #saveHRPro(ReadOnlyHRPro)
     */
    void saveHRPro(ReadOnlyHRPro hrPro, Path filePath) throws IOException;

}
