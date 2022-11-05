package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlySurvin;

/**
 * Represents a storage for {@link seedu.address.model.Survin}.
 */
public interface SurvinStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSurvinFilePath();

    /**
     * Returns Survin data as a {@link ReadOnlySurvin}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySurvin> readSurvin() throws DataConversionException, IOException;

    /**
     * @see #getSurvinFilePath()
     */
    Optional<ReadOnlySurvin> readSurvin(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySurvin} to the storage.
     * @param survin cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSurvin(ReadOnlySurvin survin) throws IOException;

    /**
     * @see #saveSurvin(ReadOnlySurvin)
     */
    void saveSurvin(ReadOnlySurvin survin, Path filePath) throws IOException;

}
