package nus.climods.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import nus.climods.commons.core.LogsCenter;
import nus.climods.commons.exceptions.DataConversionException;
import nus.climods.model.ReadOnlyUserPrefs;
import nus.climods.model.UserPrefs;
import nus.climods.model.module.Module;
import nus.climods.model.module.ReadOnlyModuleList;
import nus.climods.storage.module.ModuleListStorage;
import nus.climods.storage.module.user.UserModuleListStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);

    private UserPrefsStorage userPrefsStorage;
    private ModuleListStorage moduleListStorage;
    private UserModuleListStorage userModuleListStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ModuleListStorage moduleListStorage, UserModuleListStorage userModuleListStorage,
        UserPrefsStorage userPrefsStorage) {
        this.userPrefsStorage = userPrefsStorage;

        this.moduleListStorage = moduleListStorage;
        this.userModuleListStorage = userModuleListStorage;
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

    @Override
    public Path getModuleListFilePath() {
        return null;
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList(String academicYear) throws DataConversionException {
        return Optional.empty();
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList(String academicYear, Path filePath)
            throws DataConversionException {
        return Optional.empty();
    }

    @Override
    public void saveModuleList(List<Module> modules) throws IOException {

    }

    @Override
    public void saveModuleList(List<Module> modules, Path filePath) throws IOException {

    }
}
