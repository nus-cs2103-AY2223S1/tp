package seedu.hrpro.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.hrpro.commons.exceptions.DataConversionException;
import seedu.hrpro.model.ReadOnlyHrPro;
import seedu.hrpro.model.ReadOnlyUserPrefs;
import seedu.hrpro.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends HrProStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getHrProFilePath();

    @Override
    Optional<ReadOnlyHrPro> readHrPro() throws DataConversionException, IOException;

    @Override
    void saveHrPro(ReadOnlyHrPro hrPro) throws IOException;

}
