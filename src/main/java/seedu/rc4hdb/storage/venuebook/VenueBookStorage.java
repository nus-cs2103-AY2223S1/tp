package seedu.rc4hdb.storage.venuebook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.rc4hdb.commons.exceptions.DataConversionException;
import seedu.rc4hdb.model.ReadOnlyVenueBook;

/**
 * Represents a storage for {@link seedu.rc4hdb.model.VenueBook}.
 */
public interface VenueBookStorage {

    /**
     * Reads the venue book file data and returns a {@code ReadOnlyVenueBook}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    Optional<ReadOnlyVenueBook> readVenueBook(Path folderPath) throws DataConversionException, IOException;

    /**
     * Saves the data in {@code venueBook} to the venue_data.json file in the directory with path
     * {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     */
    void saveVenueBook(ReadOnlyVenueBook venueBook, Path folderPath) throws IOException;

    /**
     * Deletes the venue book data file that is in the directory with path {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     */
    void deleteVenueBookFile(Path folderPath) throws IOException;

    /**
     * Creates an empty venue book data file that is in the directory with path {@code folderPath}.
     *
     * @param folderPath location of the directory which holds the data. Cannot be null.
     */
    void createVenueBookFile(Path folderPath) throws IOException;

}
