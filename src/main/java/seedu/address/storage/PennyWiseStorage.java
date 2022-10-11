package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.PennyWise;
import seedu.address.model.ReadOnlyPennyWise;

/**
 * Represents a storage for {@link PennyWise}.
 */
public interface PennyWiseStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPennyWiseFilePath();

    /**
     * Returns PennyWise data as a {@link ReadOnlyPennyWise}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPennyWise> readPennyWise() throws DataConversionException, IOException;

    /**
     * @see #getPennyWiseFilePath()
     */
    Optional<ReadOnlyPennyWise> readPennyWise(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPennyWise} to the storage.
     * @param pennyWise cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePennyWise(ReadOnlyPennyWise pennyWise) throws IOException;

    /**
     * @see #savePennyWise(ReadOnlyPennyWise)
     */
    void savePennyWise(ReadOnlyPennyWise pennyWise, Path filePath) throws IOException;

}
