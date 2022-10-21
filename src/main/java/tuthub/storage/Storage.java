package tuthub.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import tuthub.commons.exceptions.DataConversionException;
import tuthub.model.ReadOnlyTuthub;
import tuthub.model.ReadOnlyUserPrefs;
import tuthub.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TuthubStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTuthubFilePath();

    @Override
    Optional<ReadOnlyTuthub> readTuthub() throws DataConversionException, IOException;

    @Override
    void saveTuthub(ReadOnlyTuthub tuthub) throws IOException;

}
