package seedu.taassist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.taassist.commons.exceptions.DataConversionException;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.TaAssist;

/**
 * Represents a storage for {@link TaAssist}.
 */
public interface TaAssistStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyTaAssist}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTaAssist> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyTaAssist> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTaAssist} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyTaAssist addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyTaAssist)
     */
    void saveAddressBook(ReadOnlyTaAssist addressBook, Path filePath) throws IOException;

}
