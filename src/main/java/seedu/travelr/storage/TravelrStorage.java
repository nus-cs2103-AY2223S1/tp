package seedu.travelr.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.travelr.commons.exceptions.DataConversionException;
import seedu.travelr.model.ReadOnlyTravelr;
import seedu.travelr.model.Travelr;

/**
 * Represents a storage for {@link Travelr}.
 */
public interface TravelrStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTravelrFilePath();

    /**
     * Returns Travelr data as a {@link ReadOnlyTravelr}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTravelr> readTravelr() throws DataConversionException, IOException;

    /**
     * @see #getTravelrFilePath()
     */
    Optional<ReadOnlyTravelr> readTravelr(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTravelr} to the storage.
     * @param travelr cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTravelr(ReadOnlyTravelr travelr) throws IOException;

    /**
     * @see #saveTravelr(ReadOnlyTravelr)
     */
    void saveTravelr(ReadOnlyTravelr travelr, Path filePath) throws IOException;

}
