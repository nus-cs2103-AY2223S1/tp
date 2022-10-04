package hobbylist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import hobbylist.commons.core.LogsCenter;
import hobbylist.commons.exceptions.DataConversionException;
import hobbylist.model.ReadOnlyHobbyList;
import hobbylist.model.ReadOnlyUserPrefs;
import hobbylist.model.UserPrefs;

/**
 * Manages storage of HobbyList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private HobbyListStorage hobbyListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code HobbyListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(HobbyListStorage hobbyListStorage, UserPrefsStorage userPrefsStorage) {
        this.hobbyListStorage = hobbyListStorage;
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


    // ================ HobbyList methods ==============================

    @Override
    public Path getHobbyListFilePath() {
        return hobbyListStorage.getHobbyListFilePath();
    }

    @Override
    public Optional<ReadOnlyHobbyList> readHobbyList() throws DataConversionException, IOException {
        return readHobbyList(hobbyListStorage.getHobbyListFilePath());
    }

    @Override
    public Optional<ReadOnlyHobbyList> readHobbyList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return hobbyListStorage.readHobbyList(filePath);
    }

    @Override
    public void saveHobbyList(ReadOnlyHobbyList hobbyList) throws IOException {
        saveHobbyList(hobbyList, hobbyListStorage.getHobbyListFilePath());
    }

    @Override
    public void saveHobbyList(ReadOnlyHobbyList hobbyList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        hobbyListStorage.saveHobbyList(hobbyList, filePath);
    }

}
