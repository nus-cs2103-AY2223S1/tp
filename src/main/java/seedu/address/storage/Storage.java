package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFindMyIntern;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends FindMyInternStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFindMyInternFilePath();

    @Override
    Optional<ReadOnlyFindMyIntern> readFindMyIntern() throws DataConversionException, IOException;

    @Override
    void saveFindMyIntern(ReadOnlyFindMyIntern addressBook) throws IOException;

}
