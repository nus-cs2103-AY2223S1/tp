package modtrekt.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import modtrekt.commons.exceptions.DataConversionException;
import modtrekt.model.ReadOnlyModuleList;
import modtrekt.model.ReadOnlyUserPrefs;
import modtrekt.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ModuleListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getModuleListFilePath();

    @Override
    Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException, IOException;

    @Override
    void saveModuleList(ReadOnlyModuleList addressBook) throws IOException;

}
