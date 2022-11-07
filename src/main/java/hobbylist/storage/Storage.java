package hobbylist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import hobbylist.commons.exceptions.DataConversionException;
import hobbylist.model.ReadOnlyHobbyList;
import hobbylist.model.ReadOnlyUserPrefs;
import hobbylist.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends HobbyListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getHobbyListFilePath();

    @Override
    Optional<ReadOnlyHobbyList> readHobbyList() throws DataConversionException, IOException;

    @Override
    void saveHobbyList(ReadOnlyHobbyList hobbyList) throws IOException;

}
