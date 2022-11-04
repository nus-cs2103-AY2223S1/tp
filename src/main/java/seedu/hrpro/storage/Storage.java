package seedu.hrpro.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.hrpro.commons.exceptions.DataConversionException;
import seedu.hrpro.model.ReadOnlyHRPro;
import seedu.hrpro.model.ReadOnlyUserPrefs;
import seedu.hrpro.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends HRProStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getHRProFilePath();

    @Override
    Optional<ReadOnlyHRPro> readHRPro() throws DataConversionException, IOException;

    @Override
    void saveHRPro(ReadOnlyHRPro hrPro) throws IOException;

}
