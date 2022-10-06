package friday.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import friday.commons.exceptions.DataConversionException;
import friday.model.Friday;
import friday.model.ReadOnlyFriday;

/**
 * Represents a storage for {@link Friday}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyFriday}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFriday> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyFriday> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFriday} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyFriday addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyFriday)
     */
    void saveAddressBook(ReadOnlyFriday addressBook, Path filePath) throws IOException;

}
