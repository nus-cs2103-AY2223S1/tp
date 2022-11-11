package seedu.modquik.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.modquik.commons.exceptions.DataConversionException;
import seedu.modquik.model.ReadOnlyModQuik;
import seedu.modquik.model.ReadOnlyUserPrefs;
import seedu.modquik.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ModQuikStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getModQuikFilePath();

    @Override
    Optional<ReadOnlyModQuik> readModQuik() throws DataConversionException, IOException;

    @Override
    void saveModQuik(ReadOnlyModQuik modQuik) throws IOException;

}
