package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ProfNus;
import seedu.address.model.ReadOnlyProfNus;

/**
 * Represents a storage for {@link ProfNus}.
 */
public interface ProfNusStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getProfNusFilePath();

    /**
     * Returns ProfNus data as a {@link ReadOnlyProfNus}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyProfNus> readProfNus() throws DataConversionException, IOException;

    /**
     * @see #getProfNusFilePath()
     */
    Optional<ReadOnlyProfNus> readProfNus(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyProfNus} to the storage.
     * @param profNus cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveProfNus(ReadOnlyProfNus profNus) throws IOException;

    /**
     * @see #saveProfNus(ReadOnlyProfNus)
     */
    void saveProfNus(ReadOnlyProfNus profNus, Path filePath) throws IOException;

}
