package seedu.studmap.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.studmap.commons.exceptions.DataConversionException;
import seedu.studmap.model.ReadOnlyStudMap;
import seedu.studmap.model.ReadOnlyUserPrefs;
import seedu.studmap.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends StudMapStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getStudMapFilePath();

    @Override
    Optional<ReadOnlyStudMap> readStudMap() throws DataConversionException, IOException;

    @Override
    void saveStudMap(ReadOnlyStudMap studMap) throws IOException;

}
