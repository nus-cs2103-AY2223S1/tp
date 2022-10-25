package friday.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import friday.commons.exceptions.DataConversionException;
import friday.model.ReadOnlyFriday;
import friday.model.ReadOnlyUserPrefs;
import friday.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends FridayStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFridayFilePath();

    @Override
    Optional<ReadOnlyFriday> readFriday() throws DataConversionException, IOException;

    @Override
    void saveFriday(ReadOnlyFriday friday) throws IOException;

}
