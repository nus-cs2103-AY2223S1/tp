package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyInventory;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface InventoryStorage {
    /**
     * Returns the file path of the Inventory data file.
     */
    Path getInventoryFilePath();

    /**
     * Returns Supply Items data from storage.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyInventory> readInventory() throws DataConversionException, IOException;

    /**
     * @see #getInventoryFilePath()
     */
    Optional<ReadOnlyInventory> readInventory(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyInventory} to the storage.
     * @param inventory cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInventory(ReadOnlyInventory inventory) throws IOException;

    /**
     * @see #saveInventory(ReadOnlyInventory)
     */
    void saveInventory(ReadOnlyInventory inventory, Path filePath) throws IOException;
}
