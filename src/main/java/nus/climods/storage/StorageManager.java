package nus.climods.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import nus.climods.commons.core.LogsCenter;
import nus.climods.commons.exceptions.DataConversionException;
import nus.climods.model.ReadOnlyUserPrefs;
import nus.climods.model.UserPrefs;
import nus.climods.model.module.UniqueUserModuleList;
import nus.climods.storage.exceptions.StorageException;
import nus.climods.storage.module.user.UserModuleListStorage;

/**
 * Manages storage of CliMods data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);

    private UserPrefsStorage userPrefsStorage;
    private UserModuleListStorage userModuleListStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code UserModuleListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(UserModuleListStorage userModuleListStorage, UserPrefsStorage userPrefsStorage) {
        this.userModuleListStorage = userModuleListStorage;
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

    // ================ UserModuleList methods ==============================
    public UserModuleListStorage getUserModuleListStorage() {
        return userModuleListStorage;
    }

    @Override
    public void saveUserModuleList(UniqueUserModuleList uniqueUserModuleList) throws StorageException {
        userModuleListStorage.saveUserModuleList(uniqueUserModuleList);
    }
}
