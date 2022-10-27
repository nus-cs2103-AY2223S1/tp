package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Database;
import seedu.address.model.ReadOnlyDatabase;

/**
 * Represents a storage for {@link Database}.
 */
public interface DatabaseStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns Database data as a {@link ReadOnlyDatabase}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyDatabase> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyDatabase> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyDatabase} to the storage.
     *
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyDatabase addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyDatabase)
     */
    void saveAddressBook(ReadOnlyDatabase addressBook, Path filePath) throws IOException;

}
