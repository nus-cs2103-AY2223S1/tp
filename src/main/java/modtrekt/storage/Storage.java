package modtrekt.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import modtrekt.commons.exceptions.DataConversionException;
import modtrekt.model.ReadOnlyModuleList;
import modtrekt.model.ReadOnlyTaskBook;
import modtrekt.model.ReadOnlyUserPrefs;
import modtrekt.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TaskBookStorage, ModuleListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTaskBookFilePath();

    @Override
    Optional<ReadOnlyTaskBook> readTaskBook() throws DataConversionException, IOException;

    @Override
    void saveTaskBook(ReadOnlyTaskBook taskBook) throws IOException;
    Path getModuleListFilePath();

    @Override
    Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException, IOException;

    @Override
    void saveModuleList(ReadOnlyModuleList moduleList) throws IOException;

}
