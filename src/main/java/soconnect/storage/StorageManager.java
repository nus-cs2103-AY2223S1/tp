package soconnect.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import soconnect.commons.core.LogsCenter;
import soconnect.commons.exceptions.DataConversionException;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.ReadOnlyUserPrefs;
import soconnect.model.UserPrefs;

/**
 * Manages storage of SoConnect data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SoConnectStorage soConnectStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code SoConnectStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(SoConnectStorage soConnectStorage, UserPrefsStorage userPrefsStorage) {
        this.soConnectStorage = soConnectStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ SoConnect methods ==============================

    @Override
    public Path getSoConnectFilePath() {
        return soConnectStorage.getSoConnectFilePath();
    }

    @Override
    public Optional<ReadOnlySoConnect> readSoConnect() throws DataConversionException, IOException {
        return readSoConnect(soConnectStorage.getSoConnectFilePath());
    }

    @Override
    public Optional<ReadOnlySoConnect> readSoConnect(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return soConnectStorage.readSoConnect(filePath);
    }

    @Override
    public void saveSoConnect(ReadOnlySoConnect soConnect) throws IOException {
        saveSoConnect(soConnect, soConnectStorage.getSoConnectFilePath());
    }

    @Override
    public void saveSoConnect(ReadOnlySoConnect soConnect, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        soConnectStorage.saveSoConnect(soConnect, filePath);
    }

}
