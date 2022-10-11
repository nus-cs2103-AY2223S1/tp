package nus.climods.storage;

import java.io.IOException;
import java.util.Optional;

import nus.climods.commons.exceptions.DataConversionException;
import nus.climods.model.ReadOnlyUserPrefs;
import nus.climods.model.UserPrefs;
import nus.climods.storage.module.ModuleListStorage;

/**
 * API of the Storage component
 */
public interface Storage extends UserPrefsStorage, ModuleListStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;
}
