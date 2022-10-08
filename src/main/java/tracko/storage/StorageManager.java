package tracko.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import tracko.commons.core.LogsCenter;
import tracko.commons.exceptions.DataConversionException;
import tracko.model.ReadOnlyTrackO;
import tracko.model.ReadOnlyUserPrefs;
import tracko.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TrackOStorage trackOStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TrackOStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TrackOStorage trackOStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.trackOStorage = trackOStorage;
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

    // ============== TrackO methods =========================================

    @Override
    public Path getOrdersFilePath() {
        return trackOStorage.getOrdersFilePath();
    }

    @Override
    public Optional<ReadOnlyTrackO> readTrackO() throws DataConversionException, IOException {
        return readTrackO(trackOStorage.getOrdersFilePath());
    }

    @Override
    public Optional<ReadOnlyTrackO> readTrackO(Path ordersFilePath) throws DataConversionException, IOException {
        logger.fine("[TrackO] Attempting to read data from file: " + ordersFilePath);
        return trackOStorage.readTrackO(ordersFilePath);
    }

    @Override
    public void saveTrackO(ReadOnlyTrackO trackO) throws IOException {
        saveTrackO(trackO, trackOStorage.getOrdersFilePath());
    }

    @Override
    public void saveTrackO(ReadOnlyTrackO trackO, Path ordersFilePath) throws IOException {
        logger.fine("[TrackO] Attempting to write to data file: " + ordersFilePath);
        trackOStorage.saveTrackO(trackO, ordersFilePath);
    }

}
