package hobbylist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import hobbylist.commons.exceptions.DataConversionException;
import hobbylist.model.HobbyList;
import hobbylist.model.ReadOnlyHobbyList;

/**
 * Represents a storage for {@link HobbyList}.
 */
public interface HobbyListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyHobbyList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyHobbyList> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyHobbyList> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyHobbyList} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyHobbyList addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyHobbyList)
     */
    void saveAddressBook(ReadOnlyHobbyList addressBook, Path filePath) throws IOException;

}
