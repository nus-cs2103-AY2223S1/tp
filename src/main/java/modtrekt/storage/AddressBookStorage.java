package modtrekt.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import modtrekt.commons.exceptions.DataConversionException;
import modtrekt.model.TaskBook;
import modtrekt.model.ReadOnlyTaskBook;

/**
 * Represents a storage for {@link TaskBook}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyTaskBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTaskBook> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyTaskBook> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTaskBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyTaskBook addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyTaskBook)
     */
    void saveAddressBook(ReadOnlyTaskBook addressBook, Path filePath) throws IOException;

}
