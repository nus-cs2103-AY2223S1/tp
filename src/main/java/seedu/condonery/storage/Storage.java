package seedu.condonery.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.condonery.commons.exceptions.DataConversionException;
import seedu.condonery.model.ReadOnlyPropertyDirectory;
import seedu.condonery.model.ReadOnlyUserPrefs;
import seedu.condonery.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends PropertyDirectoryStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPropertyDirectoryFilePath();

    @Override
    Optional<ReadOnlyPropertyDirectory> readPropertyDirectory() throws DataConversionException, IOException;

    @Override
    void savePropertyDirectory(ReadOnlyPropertyDirectory propertyDirectory) throws IOException;

}
