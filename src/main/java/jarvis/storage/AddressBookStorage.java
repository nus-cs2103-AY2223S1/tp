package jarvis.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import jarvis.commons.exceptions.DataConversionException;
import jarvis.model.ReadOnlyStudentBook;
import jarvis.model.StudentBook;

/**
 * Represents a storage for {@link StudentBook}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyStudentBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyStudentBook> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyStudentBook> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyStudentBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyStudentBook addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyStudentBook)
     */
    void saveAddressBook(ReadOnlyStudentBook addressBook, Path filePath) throws IOException;

}
