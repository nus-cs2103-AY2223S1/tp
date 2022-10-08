package modtrekt.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import modtrekt.commons.core.LogsCenter;
import modtrekt.commons.exceptions.DataConversionException;
import modtrekt.model.ReadOnlyModuleList;
import modtrekt.model.ReadOnlyUserPrefs;
import modtrekt.model.UserPrefs;

/**
 * Manages storage of ModuleList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ModuleListStorage moduleListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ModuleListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ModuleListStorage moduleListStorage, UserPrefsStorage userPrefsStorage) {
        this.moduleListStorage = moduleListStorage;
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


    // ================ ModuleList methods ==============================

    @Override
    public Path getModuleListFilePath() {
        return moduleListStorage.getModuleListFilePath();
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException, IOException {
        return readModuleList(moduleListStorage.getModuleListFilePath());
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return moduleListStorage.readModuleList(filePath);
    }

    @Override
    public void saveModuleList(ReadOnlyModuleList addressBook) throws IOException {
        saveModuleList(addressBook, moduleListStorage.getModuleListFilePath());
    }

    @Override
    public void saveModuleList(ReadOnlyModuleList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        moduleListStorage.saveModuleList(addressBook, filePath);
    }

}
