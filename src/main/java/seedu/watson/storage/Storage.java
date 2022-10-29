package seedu.watson.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.watson.commons.exceptions.DataConversionException;
import seedu.watson.model.ReadOnlyDatabase;
import seedu.watson.model.ReadOnlyUserPrefs;
import seedu.watson.model.UserPrefs;

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
