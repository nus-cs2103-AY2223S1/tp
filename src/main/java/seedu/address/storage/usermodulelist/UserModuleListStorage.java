package seedu.address.storage.usermodulelist;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;

// TODO: Rename all "ReadOnlyAddressBook" to "ReadOnlyUserModuleList"

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface UserModuleListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getUserModuleListFilePath();

    /**
     * Returns user module list data as a {@link ReadOnlyAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAddressBook> readUserModuleList() throws DataConversionException, IOException;

    /**
     * @see #getUserModuleListFilePath()
     */
    Optional<ReadOnlyAddressBook> readUserModuleList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param userModuleList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserModuleList(ReadOnlyAddressBook userModuleList) throws IOException;

    /**
     * @see #saveUserModuleList(ReadOnlyAddressBook)
     */
    void saveUserModuleList(ReadOnlyAddressBook userModuleList, Path filePath) throws IOException;

}
