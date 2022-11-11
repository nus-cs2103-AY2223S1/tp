package coydir.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import coydir.commons.exceptions.DataConversionException;
import coydir.model.Database;
import coydir.model.ReadOnlyDatabase;

/**
 * Represents a storage for {@link Database}.
 */
public interface DatabaseStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getDatabaseFilePath();

    /**
     * Returns database data as a {@link ReadOnlyDatabase}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyDatabase> readDatabase() throws DataConversionException, IOException;

    /**
     * @see #getDatabaseFilePath()
     */
    Optional<ReadOnlyDatabase> readDatabase(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyDatabase} to the storage.
     * @param database cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDatabase(ReadOnlyDatabase database) throws IOException;

    /**
     * @see #saveDatabase(ReadOnlyDatabase)
     */
    void saveDatabase(ReadOnlyDatabase database, Path filePath) throws IOException;

}
