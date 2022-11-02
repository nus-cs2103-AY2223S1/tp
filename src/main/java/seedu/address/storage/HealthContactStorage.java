package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyHealthContact;

/**
 * Represents a storage for {@link seedu.address.model.HealthContact}.
 */
public interface HealthContactStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getHealthContactFilePath();

    /**
     * Returns HealthContact data as a {@link ReadOnlyHealthContact}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyHealthContact> readHealthContact() throws DataConversionException, IOException;

    /**
     * @see #getHealthContactFilePath()
     */
    Optional<ReadOnlyHealthContact> readHealthContact(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyHealthContact} to the storage.
     * @param healthContact cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveHealthContact(ReadOnlyHealthContact healthContact) throws IOException;

    /**
     * @see #saveHealthContact(ReadOnlyHealthContact)
     */
    void saveHealthContact(ReadOnlyHealthContact healthContact, Path filePath) throws IOException;

}
