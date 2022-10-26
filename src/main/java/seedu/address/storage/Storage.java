package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyMassLinkers;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends MassLinkersStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getMassLinkersFilePath();

    @Override
    Optional<ReadOnlyMassLinkers> readMassLinkers() throws DataConversionException, IOException;

    @Override
    void saveMassLinkers(ReadOnlyMassLinkers massLinkers) throws IOException;

}
