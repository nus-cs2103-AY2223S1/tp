package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTrackAScholar;
import seedu.address.model.TrackAScholar;

/**
 * Represents a storage for {@link TrackAScholar}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns TrackAScholar data as a {@link ReadOnlyTrackAScholar}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTrackAScholar> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyTrackAScholar> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTrackAScholar} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyTrackAScholar addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyTrackAScholar)
     */
    void saveAddressBook(ReadOnlyTrackAScholar addressBook, Path filePath) throws IOException;

}
