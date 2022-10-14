package soconnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import soconnect.commons.exceptions.DataConversionException;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.SoConnect;

/**
 * Represents a storage for {@link SoConnect}.
 */
public interface SoConnectStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSoConnectFilePath();

    /**
     * Returns SoConnect data as a {@link ReadOnlySoConnect}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException If the data in storage is not in the expected format.
     * @throws IOException If there was any problem when reading from the storage.
     */
    Optional<ReadOnlySoConnect> readSoConnect() throws DataConversionException, IOException;

    /**
     * @see #getSoConnectFilePath()
     */
    Optional<ReadOnlySoConnect> readSoConnect(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySoConnect} to the storage.
     *
     * @param soConnect Cannot be null.
     * @throws IOException If there was any problem writing to the file.
     */
    void saveSoConnect(ReadOnlySoConnect soConnect) throws IOException;

    /**
     * @see #saveSoConnect(ReadOnlySoConnect)
     */
    void saveSoConnect(ReadOnlySoConnect soConnect, Path filePath) throws IOException;

}
