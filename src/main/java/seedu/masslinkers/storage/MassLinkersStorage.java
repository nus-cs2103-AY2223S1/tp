package seedu.masslinkers.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.masslinkers.commons.exceptions.DataConversionException;
import seedu.masslinkers.model.ReadOnlyMassLinkers;

/**
 * Represents a storage for {@link seedu.masslinkers.model.MassLinkers}.
 */
public interface MassLinkersStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMassLinkersFilePath();

    /**
     * Returns MassLinkers data as a {@link ReadOnlyMassLinkers}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMassLinkers> readMassLinkers() throws DataConversionException, IOException;

    /**
     * @see #getMassLinkersFilePath()
     */
    Optional<ReadOnlyMassLinkers> readMassLinkers(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMassLinkers} to the storage.
     * @param massLinkers cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMassLinkers(ReadOnlyMassLinkers massLinkers) throws IOException;

    /**
     * @see #saveMassLinkers(ReadOnlyMassLinkers)
     */
    void saveMassLinkers(ReadOnlyMassLinkers massLinkers, Path filePath) throws IOException;

}
