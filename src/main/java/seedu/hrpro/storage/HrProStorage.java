package seedu.hrpro.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.hrpro.commons.exceptions.DataConversionException;
import seedu.hrpro.model.ReadOnlyHrPro;

/**
 * Represents a storage for {@link seedu.hrpro.model.HrPro}.
 */
public interface HrProStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getHrProFilePath();

    /**
     * Returns HrPro data as a {@link ReadOnlyHrPro}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyHrPro> readHrPro() throws DataConversionException, IOException;

    /**
     * @see #getHrProFilePath()
     */
    Optional<ReadOnlyHrPro> readHrPro(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyHrPro} to the storage.
     * @param hrPro cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveHrPro(ReadOnlyHrPro hrPro) throws IOException;

    /**
     * @see #saveHrPro(ReadOnlyHrPro)
     */
    void saveHrPro(ReadOnlyHrPro hrPro, Path filePath) throws IOException;

}
