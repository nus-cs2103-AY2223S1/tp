package coydir.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import coydir.commons.exceptions.DataConversionException;
import coydir.model.ReadOnlyDatabase;
import coydir.model.ReadOnlyUserPrefs;
import coydir.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends DatabaseStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getDatabaseFilePath();

    @Override
    Optional<ReadOnlyDatabase> readDatabase() throws DataConversionException, IOException;

    @Override
    void saveDatabase(ReadOnlyDatabase database) throws IOException;

}
